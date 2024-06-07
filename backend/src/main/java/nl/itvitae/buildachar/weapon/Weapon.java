package nl.itvitae.buildachar.weapon;

import jakarta.persistence.*;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.itvitae.buildachar.characterclass.CharacterClass;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Weapon {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String name;

  @Column(nullable = false, length = 999)
  private String description;

  private WeaponType weaponType;

  private Double attackPower;

  @ManyToMany private Set<CharacterClass> characterClasses;

  public Weapon(String name, String description, WeaponType weaponType, Double attackPower) {
    this.name = name;
    this.description = description;
    this.weaponType = weaponType;
    this.attackPower = attackPower;
  }
}
