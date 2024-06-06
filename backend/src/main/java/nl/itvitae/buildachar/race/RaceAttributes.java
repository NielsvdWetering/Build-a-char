package nl.itvitae.buildachar.race;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class RaceAttributes {
  public RaceAttributes() {}

  public RaceAttributes(
      Double baseStrength,
      Double baseDexterity,
      Double baseConstitution,
      Double baseIntelligence,
      Double baseWisdom,
      Double baseCharisma) {
    this.baseStrength = baseStrength;
    this.baseDexterity = baseDexterity;
    this.baseConstitution = baseConstitution;
    this.baseIntelligence = baseIntelligence;
    this.baseWisdom = baseWisdom;
    this.baseCharisma = baseCharisma;
  }

  private Double baseStrength;
  private Double baseDexterity;
  private Double baseConstitution;
  private Double baseIntelligence;
  private Double baseWisdom;
  private Double baseCharisma;
}
