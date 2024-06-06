package nl.itvitae.buildachar.race;

import jakarta.persistence.*;
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

  private Integer baseStrength;
  private Integer baseDexterity;
  private Integer baseConstitution;
  private Integer baseIntelligence;
  private Integer baseWisdom;
  private Integer baseCharisma;

  @Setter
  @OneToMany(mappedBy = "race")
  private Set<PlayerCharacter> playerCharacters;

  public Race(
      Integer baseStrength,
      Integer baseDexterity,
      Integer baseConstitution,
      Integer baseIntelligence,
      Integer baseWisdom,
      Integer baseCharisma) {
    this.baseStrength = baseStrength;
    this.baseDexterity = baseDexterity;
    this.baseConstitution = baseConstitution;
    this.baseIntelligence = baseIntelligence;
    this.baseWisdom = baseWisdom;
    this.baseCharisma = baseCharisma;
  }

  public Race(
      String name,
      Integer baseStrength,
      Integer baseDexterity,
      Integer baseConstitution,
      Integer baseIntelligence,
      Integer baseWisdom,
      Integer baseCharisma,
      Set<PlayerCharacter> playerCharacters) {
    this.name = name;
    this.baseStrength = baseStrength;
    this.baseDexterity = baseDexterity;
    this.baseConstitution = baseConstitution;
    this.baseIntelligence = baseIntelligence;
    this.baseWisdom = baseWisdom;
    this.baseCharisma = baseCharisma;
    this.playerCharacters = playerCharacters;
  }
}
