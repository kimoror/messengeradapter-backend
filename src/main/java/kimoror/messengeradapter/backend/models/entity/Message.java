package kimoror.messengeradapter.backend.models.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "message", schema = "messenger_adapter")
@AllArgsConstructor
@NoArgsConstructor
public class Message {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "id", nullable = false)
  private long id;
  @Basic
  @Column(name = "request_id", nullable = true)
  private String request_id;
  @Basic
  @Column(name = "text", nullable = true, length = -1)
  private String text;
  @Basic
  @Column(name = "title", nullable = true)
  private String title;
  @Column(name = "footer", nullable = true)
  private String footer;
  @Basic
  @Column(name = "media", nullable = true)
  private String media;
  @Basic
  @Column(name = "file_name", nullable = true, length = 50)
  private String fileName;
  @Basic
  @Column(name = "bot_id", nullable = true)
  private Integer botId;
  @Basic
  @Column(name = "to_phone_number", nullable = true, length = 20)
  private String toPhoneNumber;
  @Basic
  @Column(name = "status", nullable = true, length = 30)
  private String status;
  @Basic
  @Column(name = "proc_note", nullable = true, length = 255)
  private String procNote;

}
