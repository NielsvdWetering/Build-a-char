package nl.itvitae.buildachar.character;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nl.itvitae.buildachar.characterclass.CharacterClass;

import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
public class PlayerCharacter {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    private String description;

    @ManyToOne
    private CharacterClass characterClass;

    public PlayerCharacter(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
