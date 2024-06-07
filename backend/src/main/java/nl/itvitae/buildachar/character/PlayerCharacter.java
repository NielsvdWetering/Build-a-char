package nl.itvitae.buildachar.character;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.itvitae.buildachar.armor.Armor;
import nl.itvitae.buildachar.characterclass.CharacterClass;
import nl.itvitae.buildachar.race.Race;
import nl.itvitae.buildachar.tool.Tool;
import nl.itvitae.buildachar.weapon.Weapon;

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

  @Setter @ManyToOne private CharacterClass characterClass;

  @ManyToMany private Set<Armor> armors;

  @Setter @ManyToOne private Race race;

  @Setter
  @ManyToMany(mappedBy = "playerCharacters", cascade = CascadeType.ALL)
  private Set<Tool> tools = new HashSet<>();

  @Setter
  @ManyToMany(mappedBy = "playerCharacters", cascade = CascadeType.ALL)
  private Set<Weapon> weapons = new HashSet<>();

  public PlayerCharacter(String name, String description) {
    this.name = name;
    this.description = description;
  }
}
