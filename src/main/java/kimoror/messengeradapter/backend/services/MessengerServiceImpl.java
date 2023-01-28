package kimoror.messengeradapter.backend.services;

import java.util.Optional;
import kimoror.messengeradapter.backend.repositories.MessengerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessengerServiceImpl implements MessengerService {

  private final MessengerRepository messengerRepository;

  @Override
  public String getRouteByBotId(int botId) {
    Optional<String> route = messengerRepository.getRouteByBotId(botId);
    if (route.isPresent()) {
      return route.get();
    } else {
      throw new RuntimeException(
          String.format("Route of messenger with id \"{}\" not found", botId));
    }
  }
}
