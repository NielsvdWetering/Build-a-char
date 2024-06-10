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
  private String name;

  @Embedded private RaceAttributes raceAttributes;

  public Race(String name, RaceAttributes raceAttributes) {
    this.name = name;
    this.raceAttributes = raceAttributes;
  }
}

// embedded fields
