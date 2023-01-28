package kimoror.messengeradapter.backend.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import kimoror.messengeradapter.backend.mappers.MessageMapper;
import kimoror.messengeradapter.backend.models.dto.MessageDto;
import kimoror.messengeradapter.backend.models.entity.Message;
import kimoror.messengeradapter.backend.repositories.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService{

  private final MessageRepository messageRepository;
  private final MessageMapper messageMapper;
  private final KafkaTemplate<String, String> messageKafkaTemplate;
  private final ObjectMapper objectMapper;

  private final MessengerService messengerService;

  @Override
  public MessageDto processMessage(MessageDto messageDto){
    save(messageDto);
    String route = messengerService.getRouteByBotId(messageDto.getBotId());
    try {
      sendMessageToKafka(route, objectMapper.writeValueAsString(messageDto));
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
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
    if(message != null) {
      messageRepository.save(message);
    }
    return 0;
  }
}
