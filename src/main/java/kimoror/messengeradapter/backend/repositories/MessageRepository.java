package kimoror.messengeradapter.backend.repositories;

import kimoror.messengeradapter.backend.models.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {}
