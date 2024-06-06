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

  private Double baseStrength;
  private Double baseDexterity;
  private Double baseConstitution;
  private Double baseIntelligence;
  private Double baseWisdom;
  private Double baseCharisma;

  @Setter
  @OneToMany(mappedBy = "race")
  private Set<PlayerCharacter> playerCharacters = new HashSet<>();

  public void addToPlayerCharacters(Set<PlayerCharacter> characters) {
    playerCharacters.addAll(characters);
  }

  public Race(
      Double baseStrength,
      Double baseDexterity,
      Double baseConstitution,
      Double baseIntelligence,
      Double baseWisdom,
      Double baseCharisma) {
    this.baseStrength = baseStrength;
    this.baseDexterity = baseDexterity;
    this.baseConstitution = baseConstitution;
    this.baseIntelligence = baseIntelligence;
    this.baseWisdom = baseWisdom;
    this.baseCharisma = baseCharisma;
  }

  public Race(
      String name,
      Double baseStrength,
      Double baseDexterity,
      Double baseConstitution,
      Double baseIntelligence,
      Double baseWisdom,
      Double baseCharisma) {
    this.name = name;
    this.baseStrength = baseStrength;
    this.baseDexterity = baseDexterity;
    this.baseConstitution = baseConstitution;
    this.baseIntelligence = baseIntelligence;
    this.baseWisdom = baseWisdom;
    this.baseCharisma = baseCharisma;
  }
}
