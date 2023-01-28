package kimoror.messengeradapter.backend.controller;

import kimoror.messengeradapter.backend.models.dto.MessageDto;
import kimoror.messengeradapter.backend.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/message")
@RequiredArgsConstructor
public class SendMessageController {

  private final MessageService messageService;


  @PostMapping("/send")
  public ResponseEntity responseBody(@RequestBody MessageDto messageDto) {
    messageService.save(messageDto);
    return ResponseEntity.ok().build();
  }
}
