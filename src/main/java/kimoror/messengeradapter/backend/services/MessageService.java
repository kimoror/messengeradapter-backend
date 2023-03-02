package kimoror.messengeradapter.backend.services;

import kimoror.messengeradapter.backend.models.dto.MessageDto;

public interface MessageService {

  MessageDto processMessage(MessageDto messageDto);

  void setMessageStatus(String statusJsonMessage);
}
