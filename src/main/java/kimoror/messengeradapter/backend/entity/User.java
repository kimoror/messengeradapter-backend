package kimoror.messengeradapter.backend.entity;

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
public class User {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "id", nullable = false)
  private int id;
  @Basic
  @Column(name = "phone_number", nullable = false, length = 20)
  private String phoneNumber;
  @Basic
  @Column(name = "fio", nullable = true, length = 255)
  private String fio;
  @Basic
  @Column(name = "nickname", nullable = true)
  private Object nickname;
  @ManyToOne
  @JoinColumn(name = "phone_number", referencedColumnName = "to_phone_number", nullable = false)
  private Message messageByPhoneNumber;
}
