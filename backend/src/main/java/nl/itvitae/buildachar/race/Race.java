package nl.itvitae.buildachar.race;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.itvitae.buildachar.character.PlayerCharacter;

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

  @Setter
  @OneToMany(mappedBy = "race")
  private Set<PlayerCharacter> playerCharacters = new HashSet<>();

  public Race(String name, RaceAttributes raceAttributes) {
    this.name = name;
    this.raceAttributes = raceAttributes;
  }

  public void addToPlayerCharacters(Set<PlayerCharacter> characters) {
    playerCharacters.addAll(characters);
  }
}

// embedded fields
