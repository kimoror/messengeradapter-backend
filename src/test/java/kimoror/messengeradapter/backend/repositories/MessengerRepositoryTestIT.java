package kimoror.messengeradapter.backend.repositories;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
import kimoror.messengeradapter.backend.BackendApplication;
import kimoror.messengeradapter.backend.models.entity.Bot;
import kimoror.messengeradapter.backend.models.entity.Messenger;
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
public class MessengerRepositoryTestIT {

    @Value("classpath:sql/create.sql")
    private Resource createScript;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    BotsRepository botsRepository;

    @Autowired
    MessengerRepository messengerRepository;

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
    void testGetMessengerByBotId(){
        //given
        int messengerId = 1;
        String messengerName = "telegram";
        boolean messengerActive = true;
        String messengerRoute = "messenger.telegram";
        int botId = 1;
        String botName = "dummy_test_bot";
        String botCredentials = "secret_password";
        Messenger messenger = new Messenger(messengerId, messengerName, messengerActive, messengerRoute);
        Bot bot = new Bot(botId, messengerId, botName, botCredentials);
        messengerRepository.save(messenger);
        botsRepository.save(bot);

        //when
        Optional<Messenger> result = messengerRepository.getMessengerByBotId(1);

        //then
        Assertions.assertEquals(messenger, result.get());
    }

}
