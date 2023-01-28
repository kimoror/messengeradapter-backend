package kimoror.messengeradapter.backend.services;

import kimoror.messengeradapter.backend.models.dto.MessageDto;

public interface MessageService {

  public int save(MessageDto messageDto);
}
