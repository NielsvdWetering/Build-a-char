package nl.itvitae.buildachar.armor;

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
public class Armor {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String name;

  @Setter private String description;

  @Setter private double defence;

  @Setter private ArmorType armorType;

  @Setter private ArmorClass armorClass;
  
  @Setter
  @ManyToMany(mappedBy = "allowedArmors")
  private Set<CharacterClass> requiredClasses;

  public Armor(
      String name, String description, double defence, ArmorType armorType, ArmorClass armorClass) {
    this.name = name;
    this.description = description;
    this.defence = defence;
    this.armorType = armorType;
    this.armorClass = armorClass;
  }
}
