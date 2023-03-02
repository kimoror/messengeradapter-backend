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
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

  private final MessageRepository messageRepository;
  private final MessageMapper messageMapper;
  private final MessageToOutcomeMessageMapper messageToOutcomeMessageMapper;
  private final KafkaTemplate<String, String> messageKafkaTemplate;
  private final ObjectMapper objectMapper;

  private final MessengerService messengerService;
  private final BotsService botsService;

  private final UserService userService;

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
      sendMessageToKafka(route, outcomeMessage);
    } else {
      throw new RuntimeException("Outcome message is empty. The message was not sent");
    }

    return messageDto;
  }

  private void sendMessageToKafka(String route, String messageJson) {
    messageKafkaTemplate.send(route, messageJson);
  }

  private int save(MessageDto messageDto) {
    Message message = null;
    try {
      message = messageMapper.convert(messageDto);
    } catch (IOException e) {
      throw new RuntimeException("Failed to convert MessageDto to message entity", e);
    }
    if (message != null) {
      messageRepository.save(message);
    }
    return 0;
  }
}
