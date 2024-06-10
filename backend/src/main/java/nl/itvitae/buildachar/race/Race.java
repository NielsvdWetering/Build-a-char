package nl.itvitae.buildachar.race;

import jakarta.persistence.*;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Race {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false)
  @Setter
  private String name;

  @Embedded private Stats stats;

  public Race(String name, Stats stats) {
    this.name = name;
    this.stats = stats;
  }
}
