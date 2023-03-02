package kimoror.messengeradapter.backend.models.dto;

import lombok.Data;

@Data
public class OutcomeToMessengerMessageDto {

  private String requestId;
  private String botName;
  private String toChatId;
  private String photoUrl;
  private String videoUrl;
  private String audioUrl;
  private String documentUrl;
  private String fileName;
  private String text;
  private String title;
  private String footer;

}
