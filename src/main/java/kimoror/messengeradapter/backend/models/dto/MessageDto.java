package kimoror.messengeradapter.backend.models.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MessageDto {
  public String text;
  private byte[] media;
  private String filename;
  private Integer botId;
  private String toPhoneNumber;

}
