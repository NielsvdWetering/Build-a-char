package nl.itvitae.buildachar.tool;

import jakarta.persistence.*;
import java.util.Set;
import java.util.UUID;
import nl.itvitae.buildachar.character.PlayerCharacter;

@Entity
public class Tool {
  @Id
  @GeneratedValue
  private UUID id;

  @Column(nullable = false, length = 999)
  private String description;

  @ManyToMany private Set<PlayerCharacter> playerCharacters;
}
