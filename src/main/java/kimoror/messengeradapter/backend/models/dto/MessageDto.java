package kimoror.messengeradapter.backend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageDto {
  private String requestId;
  private String title;
  private String footer;
  private String text;
  private String fileName;
  private Integer botId;
  private String photoUrl;
  private String videoUrl;
  private String audioUrl;
  private String documentUrl;
  private String toPhoneNumber;

}
