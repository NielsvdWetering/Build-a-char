package nl.itvitae.buildachar.weapon;

import jakarta.persistence.*;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.itvitae.buildachar.character.PlayerCharacter;
import nl.itvitae.buildachar.characterclass.CharacterClass;

@Entity
@NoArgsConstructor
@Getter
public class Weapon {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String name;

  @Column(nullable = false, length = 999)
  private String description;

  private String type;

  private Double attackPower;

  @Setter
  @ManyToMany
  @JoinTable(
      name = "weapon_player_character",
      joinColumns = @JoinColumn(name = "weapon_id"),
      inverseJoinColumns = @JoinColumn(name = "player_character_id"))
  private Set<PlayerCharacter> playerCharacters;

  @Setter @ManyToMany private Set<CharacterClass> characterClasses;

  public Weapon(String name, String description, String type, Double attackPower) {
    this.name = name;
    this.description = description;
    this.type = type;
    this.attackPower = attackPower;
  }
}
