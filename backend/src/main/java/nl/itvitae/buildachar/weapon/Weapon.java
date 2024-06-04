package nl.itvitae.buildachar.weapon;

import jakarta.persistence.*;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nl.itvitae.buildachar.character.PlayerCharacter;
import nl.itvitae.buildachar.characterclass.CharacterClass;

@Entity
@NoArgsConstructor
@Getter
public class Weapon {
  @Id
  @GeneratedValue
  private UUID id;

  private Integer attackPower;

  @Column(nullable = false, length = 999)
  private String description;

  private String type;

  @ManyToMany private Set<PlayerCharacter> playerCharacters;

  @ManyToMany private Set<CharacterClass> characterClasses;
}
