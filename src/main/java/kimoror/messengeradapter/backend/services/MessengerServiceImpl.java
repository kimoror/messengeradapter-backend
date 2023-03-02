package kimoror.messengeradapter.backend.services;

import java.util.NoSuchElementException;
import java.util.Optional;
import kimoror.messengeradapter.backend.models.entity.Messenger;
import kimoror.messengeradapter.backend.repositories.MessengerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessengerServiceImpl implements MessengerService {

  private final MessengerRepository messengerRepository;

  @Override
  public Messenger getMessengerByBotId(int botId) {
    Optional<Messenger> messenger = messengerRepository.getMessengerByBotId(botId);
    if (messenger.isPresent()) {
      return messenger.get();
    } else {
      throw new NoSuchElementException();
    }
  }
}
