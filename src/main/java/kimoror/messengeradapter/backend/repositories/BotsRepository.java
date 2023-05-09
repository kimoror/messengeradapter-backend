package kimoror.messengeradapter.backend.repositories;

import java.util.Optional;
import kimoror.messengeradapter.backend.models.entity.Bot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BotsRepository extends JpaRepository<Bot, Integer> {

  Optional<Bot> getBotsById(int id);
}
