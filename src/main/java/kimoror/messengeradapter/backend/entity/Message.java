package kimoror.messengeradapter.backend.entity;

import java.util.Arrays;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Message {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "id", nullable = false)
  private long id;
  @Basic
  @Column(name = "text", nullable = true, length = -1)
  private String text;
  @Basic
  @Column(name = "media", nullable = true)
  private byte[] media;
  @Basic
  @Column(name = "filename", nullable = true, length = 50)
  private String filename;
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
  @ManyToOne
  @JoinColumn(name = "bot_id", referencedColumnName = "id")
  private Bots botsByBotId;

}
