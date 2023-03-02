package kimoror.messengeradapter.backend.repositories;

import kimoror.messengeradapter.backend.models.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

  @Transactional
  @Modifying
  @Query("UPDATE Message m SET m.status = :status where m.request_id = :messageId")
  void setStatusByMessageId(@Param("messageId") String messageId, @Param("status") String status);

}
