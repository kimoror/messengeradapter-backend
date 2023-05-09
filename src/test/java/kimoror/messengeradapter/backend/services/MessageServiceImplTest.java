package kimoror.messengeradapter.backend.services;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import kimoror.messengeradapter.backend.mappers.MessageMapper;
import kimoror.messengeradapter.backend.mappers.MessageToOutcomeMessageMapper;
import kimoror.messengeradapter.backend.models.dto.MessageDto;
import kimoror.messengeradapter.backend.repositories.MessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

@ExtendWith(MockitoExtension.class)
class MessageServiceImplTest {

  @Mock
  private MessageRepository mockMessageRepository;
  @Mock
  private MessageMapper mockMessageMapper;
  @Mock
  private MessageToOutcomeMessageMapper mockMessageToOutcomeMessageMapper;
  @Mock
  private KafkaTemplate<String, String> mockMessageKafkaTemplate;
  @Mock
  private MessengerService mockMessengerService;
  @Mock
  private BotsService mockBotsService;
  @Mock
  private UserService mockUserService;

  private MessageServiceImpl messageServiceImplUnderTest;

  @BeforeEach
  void setUp() {
    messageServiceImplUnderTest = new MessageServiceImpl(mockMessageRepository, mockMessageMapper,
        mockMessageToOutcomeMessageMapper, mockMessageKafkaTemplate, new ObjectMapper(),
        mockMessengerService, mockBotsService, mockUserService);
  }
  @Test
  void testProcessMessage_MessageMapperThrowsIOException() throws Exception {
    // Setup
    final MessageDto messageDto = new MessageDto("requestId", "title", "footer", "text", "fileName",
        0, "photoUrl", "videoUrl", "audioUrl", "documentUrl", "toPhoneNumber");
    when(mockMessageMapper.convert(
        new MessageDto("requestId", "title", "footer", "text", "fileName", 0, "photoUrl",
            "videoUrl", "audioUrl", "documentUrl", "toPhoneNumber"))).thenThrow(IOException.class);

    // Run the test
    assertThatThrownBy(() -> messageServiceImplUnderTest.processMessage(messageDto))
        .isInstanceOf(RuntimeException.class);
  }
}
