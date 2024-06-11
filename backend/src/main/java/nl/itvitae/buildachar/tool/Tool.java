package nl.itvitae.buildachar.tool;

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
public class Tool {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false)
  @Setter
  private String name;

  @Column(nullable = false, length = 999)
  @Setter
  private String description;

  @Setter @ManyToMany private Set<CharacterClass> characterClasses;

  public Tool(String name, String description) {
    this.name = name;
    this.description = description;
  }
}
