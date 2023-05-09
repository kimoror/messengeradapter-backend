package kimoror.messengeradapter.backend.services;

import kimoror.messengeradapter.backend.models.entity.Messenger;

public interface MessengerService {

  Messenger getMessengerByBotId(int messengerId);
}
