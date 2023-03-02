package kimoror.messengeradapter.backend.services;

import java.util.NoSuchElementException;
import java.util.Optional;
import kimoror.messengeradapter.backend.models.entity.User;
import kimoror.messengeradapter.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

  private final UserRepository userRepository;

  @Override
  public User getUserByPhoneNumber(String phoneNumber) {
    Optional<User> user = userRepository.getUserByPhoneNumber(phoneNumber);
    if (user.isPresent()) {
      return user.get();
    } else {
      throw new NoSuchElementException();
    }
  }

  @Override
  public String getChatId(String messenger, String nicknames) throws RuntimeException {
    JSONObject jsonObject;
    try {
      jsonObject = new JSONObject(nicknames);
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
    String chatId = null;
    try {
      if(jsonObject.has(messenger)){
        chatId = jsonObject.getString(messenger);
      };

    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
    return Optional.ofNullable(chatId).orElseThrow(() -> new RuntimeException("ChatId is empty"));
  }
}
