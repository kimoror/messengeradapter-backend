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
@Table(name = "messengers", schema = "messenger_adapter")
@AllArgsConstructor
@NoArgsConstructor
public class Messenger {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "id", nullable = false)
  private int id;
  @Basic
  @Column(name = "name", nullable = false, length = 50)
  private String name;
  @Basic
  @Column(name = "active", nullable = true)
  private Boolean active;

  @Basic
  @Column(name = "route", nullable = false)
  private String route;
}
