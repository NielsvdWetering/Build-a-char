package nl.itvitae.buildachar.character;

import jakarta.persistence.*;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nl.itvitae.buildachar.armor.Armor;
import nl.itvitae.buildachar.characterclass.CharacterClass;
import nl.itvitae.buildachar.charactertool.CharacterTool;
import nl.itvitae.buildachar.race.Race;

@Entity
@NoArgsConstructor
@Getter
public class PlayerCharacter {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false)
  private String name;

  private String description;

  @ManyToOne private CharacterClass characterClass;

  @ManyToMany private Set<Armor> armors;

  @ManyToOne private Race race;

  @OneToMany private Set<CharacterTool> characterTools;

  public PlayerCharacter(String name, String description) {
    this.name = name;
    this.description = description;
  }
}
