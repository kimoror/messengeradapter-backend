package kimoror.messengeradapter.backend.repositories;

import java.util.Optional;
import kimoror.messengeradapter.backend.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> getUserByPhoneNumber(String phoneNumber);

}
