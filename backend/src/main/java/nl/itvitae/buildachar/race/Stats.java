package nl.itvitae.buildachar.race;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Stats {
  private Integer baseStrength;
  private Integer baseDexterity;
  private Integer baseConstitution;
  private Integer baseIntelligence;
  private Integer baseWisdom;
  private Integer baseCharisma;
}
