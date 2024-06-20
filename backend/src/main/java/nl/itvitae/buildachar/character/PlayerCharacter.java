package nl.itvitae.buildachar.character;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.itvitae.buildachar.armor.Armor;
import nl.itvitae.buildachar.characterclass.CharacterClass;
import nl.itvitae.buildachar.race.Race;
import nl.itvitae.buildachar.tool.Tool;
import nl.itvitae.buildachar.user.User;
import nl.itvitae.buildachar.weapon.Weapon;

@Entity
@NoArgsConstructor
@Getter
public class PlayerCharacter {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false)
  @Setter
  private String name;

  @Lob // weggehaald want dit zorgde voor problemen
  @Setter
  private String description;

  @Setter @ManyToOne private CharacterClass characterClass;

  @ManyToMany private Set<Armor> armors = new HashSet<>();

  @Setter @ManyToOne private Race race;

  @Setter @ManyToOne private Tool tool;

  @Setter @ManyToOne private Weapon weapon;

  @Setter @ManyToOne private User user;

  public PlayerCharacter(
      String name, String description, Race race, CharacterClass characterClass) {
    this.name = name;
    this.description = description;
    this.race = race;
    this.characterClass = characterClass;
  }

  public void setArmors(Set<Armor> newArmorList) {
    armors.clear();
    armors.addAll(newArmorList);
  }

  public PlayerCharacter(
      String name,
      String description,
      CharacterClass characterClass,
      Set<Armor> armors,
      Race race,
      Tool tool,
      Weapon weapon) {
    this.name = name;
    this.description = description;
    this.characterClass = characterClass;
    this.armors = armors;
    this.race = race;
    this.tool = tool;
    this.weapon = weapon;
  }

  public Optional<Weapon> getWeapon() {
    return Optional.ofNullable(weapon);
  }

  public Optional<Tool> getTool() {
    return Optional.ofNullable(tool);
  }
}
