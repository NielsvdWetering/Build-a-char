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

  private Integer attackPower;

  @Column(nullable = false, length = 999)
  private String description;

  private String type;

  @Setter
  @ManyToMany
  @JoinTable(
      name = "weapon_player_character",
      joinColumns = @JoinColumn(name = "weapon_id"),
      inverseJoinColumns = @JoinColumn(name = "player_character_id"))
  private Set<PlayerCharacter> playerCharacters;

  @Setter @ManyToMany private Set<CharacterClass> characterClasses;
}
