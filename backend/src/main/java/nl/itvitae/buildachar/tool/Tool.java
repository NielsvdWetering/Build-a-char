package nl.itvitae.buildachar.tool;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import nl.itvitae.buildachar.charactertool.CharacterTool;

import java.util.Set;
import java.util.UUID;

@Entity
public class Tool {
    private UUID id;
    @Column(nullable = false, length = 999)
    private String description;

    @OneToMany
    private Set<CharacterTool> characterTools;
}
