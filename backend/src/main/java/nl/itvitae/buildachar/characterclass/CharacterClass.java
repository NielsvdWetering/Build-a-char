package nl.itvitae.buildachar.characterclass;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.itvitae.buildachar.armor.Armor;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class CharacterClass {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false)
  private String name;

  @ManyToMany private Set<Armor> allowedArmors = new HashSet<>();

  public CharacterClass(String name) {
    this.name = name;
  }
}
