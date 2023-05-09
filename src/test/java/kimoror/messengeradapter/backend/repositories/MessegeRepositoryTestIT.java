package kimoror.messengeradapter.backend.repositories;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
import kimoror.messengeradapter.backend.BackendApplication;
import kimoror.messengeradapter.backend.models.entity.Bot;
import kimoror.messengeradapter.backend.models.entity.Message;
import kimoror.messengeradapter.backend.models.entity.Messenger;
import kimoror.messengeradapter.backend.models.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest(
    classes = {BackendApplication.class}
)
public class MessegeRepositoryTestIT {

  @Value("classpath:sql/create.sql")
  private Resource createScript;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  BotsRepository botsRepository;

  @Autowired
  MessengerRepository messengerRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  MessageRepository messageRepository;

  @BeforeEach
  void setup() throws IOException {
    jdbcTemplate.execute(new String(Files.readAllBytes(createScript.getFile().toPath())));
  }

  @AfterEach
  void teardown() {
    jdbcTemplate.execute("DROP TABLE messenger_adapter.bots cascade ");
    jdbcTemplate.execute("DROP TABLE messenger_adapter.users cascade ");
    jdbcTemplate.execute("DROP TABLE messenger_adapter.messengers cascade");
    jdbcTemplate.execute("DROP TABLE messenger_adapter.message cascade ");
  }

  @Test
  void setStatusByMessageIdTest(){
    //given
    int messengerId = 1;
    String messengerName = "telegram";
    boolean messengerActive = true;
    String messengerRoute = "messenger.telegram";
    int botId = 1;
    String botName = "dummy_test_bot";
    String botCredentials = "secret_password";
    long messageId = 1;
    String request_id = "Kimoror2311";
    String text = "SuperMEGA_TEXT";
    String title = "##BigTitle";
    String footer = "UltraSmallFooter";
    String media = "urlToMediaContent";
    String fileName = "NameOfFile";
    String phoneNumber = "89115546790";
    String status="RECEIVED";
    String procNote="";
    int userId = 1;
    String fio = "Prokopenko Ignat Andreevich";
    String nickname = "{\"telegram\": \"ukrop\"}";

    String expectedStatus = "FAILED";

    Messenger messenger = new Messenger(messengerId, messengerName, messengerActive, messengerRoute);
    Bot bot = new Bot(botId, messengerId, botName, botCredentials);
    User user = new User(userId, phoneNumber, fio, nickname);
    Message message = new Message(messageId, request_id, text, title, footer, media, fileName, botId, phoneNumber, status, procNote);
    Message expectedMessage = new Message(messageId, request_id, text, title, footer, media, fileName, botId, phoneNumber, expectedStatus, procNote);

    messengerRepository.save(messenger);
    botsRepository.save(bot);
    userRepository.save(user);
    messageRepository.save(message);

    //when
    messageRepository.setStatusByRequestId(request_id, "FAILED");

    //verify
    Optional<Message> resultMessage = messageRepository.findById(messageId);
    Assertions.assertEquals(expectedMessage, resultMessage.get());


  }

}
