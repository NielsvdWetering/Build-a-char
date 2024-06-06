package nl.itvitae.buildachar.tool;

import jakarta.persistence.*;
import java.util.Set;
import java.util.UUID;
import lombok.Setter;
import nl.itvitae.buildachar.character.PlayerCharacter;
import nl.itvitae.buildachar.characterclass.CharacterClass;

@Entity
public class Tool {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false, length = 999)
  private String description;

  @Setter
  @ManyToMany
  @JoinTable(
      name = "tool_player_character",
      joinColumns = @JoinColumn(name = "tool_id"),
      inverseJoinColumns = @JoinColumn(name = "player_character_id"))
  private Set<PlayerCharacter> playerCharacters;

  @Setter @ManyToMany private Set<CharacterClass> characterClasses;

  public Tool(String name, String description) {
    this.name = name;
    this.description = description;
  }
}
