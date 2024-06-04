package nl.itvitae.buildachar.race;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Race {
    @Id
    @GeneratedValue
    private Long id;
    @Column(length=100)
    private String name;
    private final Integer baseStrength;
    private final Integer baseDexterity;
    private final Integer baseConstitution;
    private final Integer baseIntelligence;
    private final Integer baseWisdom;
    private final Integer baseCharisma;

    //@OneToMany
    //private final Character character;

    public Race(Integer baseStrength, Integer baseDexterity, Integer baseConstitution, Integer baseIntelligence, Integer baseWisdom, Integer baseCharisma) {
        this.baseStrength = baseStrength;
        this.baseDexterity = baseDexterity;
        this.baseConstitution = baseConstitution;
        this.baseIntelligence = baseIntelligence;
        this.baseWisdom = baseWisdom;
        this.baseCharisma = baseCharisma;
    }
}
