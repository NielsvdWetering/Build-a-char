package nl.itvitae.buildachar.race;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class RaceStats {
  public RaceStats() {}

  public RaceStats(
      Integer baseStrength,
      Integer baseDexterity,
      Integer baseConstitution,
      Integer baseIntelligence,
      Integer baseWisdom,
      Integer baseCharisma) {
    this.baseStrength = baseStrength;
    this.baseDexterity = baseDexterity;
    this.baseConstitution = baseConstitution;
    this.baseIntelligence = baseIntelligence;
    this.baseWisdom = baseWisdom;
    this.baseCharisma = baseCharisma;
  }

  private Integer baseStrength;
  private Integer baseDexterity;
  private Integer baseConstitution;
  private Integer baseIntelligence;
  private Integer baseWisdom;
  private Integer baseCharisma;
}
