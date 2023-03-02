package kimoror.messengeradapter.backend.services;

import kimoror.messengeradapter.backend.models.entity.User;

public interface UserService {

  User getUserByPhoneNumber(String phoneNumber);

  String getChatId(String messenger, String nicknames);

}
