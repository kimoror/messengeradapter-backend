package kimoror.messengeradapter.backend.models.entity;

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
  private String nickname;
}
