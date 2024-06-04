package nl.itvitae.buildachar.characterclass;

import jakarta.persistence.*;
import nl.itvitae.buildachar.character.PlayerCharacter;

import java.util.Set;
import java.util.UUID;

@Entity
public class CharacterClass {
    @Id @GeneratedValue(strategy = GenerationType.UUID) private UUID id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "characterClass")
    private Set<PlayerCharacter> characters;

    public CharacterClass(String name) {
        this.name = name;
    }
}
