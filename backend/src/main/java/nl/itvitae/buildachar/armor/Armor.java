package nl.itvitae.buildachar.armor;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.itvitae.buildachar.characterclass.CharacterClass;

import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
public class Armor {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private double defence;

    private ArmorType armorType;

    private String description;

    private ArmorClass armorClass;

    @Setter
    @ManyToMany(mappedBy = "armors")
    private Set<Character> characters;

    @Setter
    @ManyToMany(mappedBy = "allowedArmors")
    private Set<CharacterClass> requiredClasses;
}
