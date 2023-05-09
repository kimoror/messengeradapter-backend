package kimoror.messengeradapter.backend.services;

import java.util.NoSuchElementException;
import java.util.Optional;
import kimoror.messengeradapter.backend.models.entity.Bot;
import kimoror.messengeradapter.backend.repositories.BotsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class BotsServiceImpl implements BotsService {

  private final BotsRepository botsRepository;

  @Override
  public Bot getBotById(int id) {
    Optional<Bot> botOpt = botsRepository.getBotsById(id);
    if (botOpt.isPresent()) {
      return botOpt.get();
    } else {
      throw new NoSuchElementException();
    }
  }
}
