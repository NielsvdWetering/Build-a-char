package nl.itvitae.buildachar.armor;

import java.util.UUID;

public record ArmorNoTypeDTO(
    UUID id, String name, String description, double defence, ArmorClass armorClass) {
  static ArmorNoTypeDTO from(Armor armor) {
    return new ArmorNoTypeDTO(
        armor.getId(),
        armor.getName(),
        armor.getDescription(),
        armor.getDefence(),
        armor.getArmorClass());
  }
}
