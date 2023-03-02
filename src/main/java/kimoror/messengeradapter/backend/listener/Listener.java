package kimoror.messengeradapter.backend.listener;

import java.util.Map;
import kimoror.messengeradapter.backend.services.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class Listener {

  private final MessageService messageService;

  @KafkaListener(topics = "${kimoror.kafka.topic.status}")
  public void processKafkaMessage(@Payload String message, @Headers Map<String, String> headers) {
    log.info(">>> {}, key={}, message: {}", headers.get(KafkaHeaders.RECEIVED_TOPIC),
        headers.get(KafkaHeaders.KEY), message);
    messageService.setMessageStatus(message);
  }
}
