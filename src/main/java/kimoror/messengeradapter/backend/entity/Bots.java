package kimoror.messengeradapter.backend.entity;

import java.util.Collection;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;

@Entity
@Data
public class Bots {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "id", nullable = false)
  private int id;
  @Basic
  @Column(name = "messenger_id", nullable = true)
  private Integer messengerId;
  @Basic
  @Column(name = "bot_name", nullable = false, length = 100)
  private String botName;
  @Basic
  @Column(name = "credentials", nullable = true, length = -1)
  private String credentials;
  @ManyToOne
  @JoinColumn(name = "messenger_id", referencedColumnName = "id")
  private Messengers messengersByMessengerId;
  @OneToMany(mappedBy = "botsByBotId")
  private Collection<Message> messagesById;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Bots bots = (Bots) o;
    return id == bots.id && Objects.equals(messengerId, bots.messengerId)
        && Objects.equals(botName, bots.botName) && Objects.equals(credentials,
        bots.credentials);
  }
}
