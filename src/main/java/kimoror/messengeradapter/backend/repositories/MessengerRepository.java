package kimoror.messengeradapter.backend.repositories;

import java.util.Optional;
import kimoror.messengeradapter.backend.models.entity.Messenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MessengerRepository extends JpaRepository<Messenger, Integer> {

  @Query(value = "SELECT m.route FROM Messenger m LEFT JOIN Bot b ON m.id = b.messengerId WHERE b.id = :botId AND b.messengerId = m.id")
  public Optional<String> getRouteByBotId(int botId);
}
