package kimoror.messengeradapter.backend.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import kimoror.messengeradapter.backend.mappers.MessageMapper;
import kimoror.messengeradapter.backend.mappers.MessageToOutcomeMessageMapper;
import kimoror.messengeradapter.backend.models.dto.MessageDto;
import kimoror.messengeradapter.backend.models.entity.Message;
import kimoror.messengeradapter.backend.models.entity.Messenger;
import kimoror.messengeradapter.backend.repositories.MessageRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@AllArgsConstructor
@Slf4j
public class MessageServiceImpl implements MessageService {

  private final MessageRepository messageRepository;
  private final MessageMapper messageMapper;
  private final MessageToOutcomeMessageMapper messageToOutcomeMessageMapper;
  private final KafkaTemplate<String, String> messageKafkaTemplate;
  private final ObjectMapper objectMapper;
  private final MessengerService messengerService;
  private final BotsService botsService;
  private final UserService userService;
  private final String requestIdParameter = "requestId";
  private final String statusParameter = "status";

  @Override
  public MessageDto processMessage(MessageDto messageDto) {
    save(messageDto);
    Messenger messenger = messengerService.getMessengerByBotId(messageDto.getBotId());
    String route = messenger.getRoute();
    String botName = botsService.getBotById(messageDto.getBotId()).getBotName();
    String toChatId = userService.getChatId(messenger.getName(),
        userService.getUserByPhoneNumber(messageDto.getToPhoneNumber()).getNickname());
    String outcomeMessage = null;
    switch (messenger.getName()) {
      case "telegram" -> {
        try {
          outcomeMessage = objectMapper.writeValueAsString(messageToOutcomeMessageMapper.convert(
              messageDto, botName, toChatId));
        } catch (JsonProcessingException e) {
          throw new RuntimeException(e);
        }
      }
      case "vk" -> {
        // TODO
      }
      default -> throw new IllegalStateException("Unexpected value: " + messageDto);
    }

    if (StringUtils.hasText(outcomeMessage)) {
      sendMessageToKafka(route, messageDto.getRequestId(), outcomeMessage);
    } else {
      throw new RuntimeException("Outcome message is empty. The message was not sent");
    }

    return messageDto;
  }

  private void sendMessageToKafka(String route, String requestId, String messageJson) {
    messageKafkaTemplate.send(route, requestId, messageJson);
  }

  private int save(MessageDto messageDto) {
    Message message = null;
    try {
      message = messageMapper.convert(messageDto);
    } catch (IOException e) {
      throw new RuntimeException("Failed to convert MessageDto to message entity", e);
    }
    if (message != null) {
      message.setStatus("RECEIVED");
      messageRepository.save(message);
    }
    return 0;
  }

  public void setMessageStatus(String statusJsonMessage) {
    JSONObject jsonObject;
    try {
      jsonObject = new JSONObject(statusJsonMessage);
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
    String requestId = null;
    String status = null;
    try {
      if (jsonObject.has(requestIdParameter)) {
        requestId = String.valueOf(jsonObject.get(requestIdParameter));
      }
      if (jsonObject.has(statusParameter)) {
        status = String.valueOf(jsonObject.get(statusParameter));
      }
    } catch (JSONException e) {
      log.error("Error when get status message: ", e);
    }
    if(StringUtils.hasText(requestId) && StringUtils.hasText(status)) {
      messageRepository.setStatusByRequestId(requestId, status);
    } else {
      log.error("Request id or status are empty");
    }
  }
}
