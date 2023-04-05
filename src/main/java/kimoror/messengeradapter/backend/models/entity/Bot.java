package kimoror.messengeradapter.backend.models.entity;

import java.util.Objects;
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
@Table(name = "bots", schema = "messenger_adapter")
@AllArgsConstructor
@NoArgsConstructor
public class Bot {

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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Bot bot = (Bot) o;
    return id == bot.id && Objects.equals(messengerId, bot.messengerId)
        && Objects.equals(botName, bot.botName) && Objects.equals(credentials,
        bot.credentials);
  }
}
