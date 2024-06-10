package nl.itvitae.buildachar.characterclass;

import jakarta.persistence.*;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.itvitae.buildachar.armor.Armor;

@Entity
@NoArgsConstructor
@Getter
public class CharacterClass {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false)
  private String name;

  @Setter @ManyToMany private Set<Armor> allowedArmors;

  public CharacterClass(String name) {
    this.name = name;
  }
}
