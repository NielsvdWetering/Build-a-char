package nl.itvitae.buildachar.armor;

import java.util.UUID;

public record ArmorDTO(
    UUID id,
    String name,
    String description,
    double defence,
    ArmorType type,
    ArmorClass armorClass) {
  public static ArmorDTO from(Armor armor) {
    return new ArmorDTO(
        armor.getId(),
        armor.getName(),
        armor.getDescription(),
        armor.getDefence(),
        armor.getArmorType(),
        armor.getArmorClass());
  }
}
