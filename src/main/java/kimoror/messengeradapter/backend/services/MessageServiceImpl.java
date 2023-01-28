package kimoror.messengeradapter.backend.services;

import java.io.IOException;
import kimoror.messengeradapter.backend.mappers.MessageMapper;
import kimoror.messengeradapter.backend.models.dto.MessageDto;
import kimoror.messengeradapter.backend.models.entity.Message;
import kimoror.messengeradapter.backend.repositories.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService{

  private final MessageRepository messageRepository;
  private final MessageMapper messageMapper;

  @Override
  public int save(MessageDto messageDto) {
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
