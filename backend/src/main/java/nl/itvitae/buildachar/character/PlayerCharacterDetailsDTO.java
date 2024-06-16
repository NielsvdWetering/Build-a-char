package nl.itvitae.buildachar.character;

import jakarta.persistence.EntityNotFoundException;
import java.util.Set;
import java.util.UUID;
import nl.itvitae.buildachar.armor.Armor;
import nl.itvitae.buildachar.armor.ArmorType;
import nl.itvitae.buildachar.race.Stats;

public record PlayerCharacterDetailsDTO(
    UUID id,
    String name,
    String description,
    String characterClass,
    String race,
    Stats stats,
    String tool,
    String weapon,
    String armorHead,
    String armorTorso,
    String armorLegs,
    String armorHands,
    String armorFeet) {
  static PlayerCharacterDetailsDTO from(PlayerCharacter playerCharacter) {
    return new PlayerCharacterDetailsDTO(
        playerCharacter.getId(),
        playerCharacter.getName(),
        playerCharacter.getDescription(),
        playerCharacter.getCharacterClass().getName(),
        playerCharacter.getRace().getName(),
        playerCharacter.getRace().getStats(),
        playerCharacter.getTool().orElseThrow(EntityNotFoundException::new).getName(),
        playerCharacter.getWeapon().orElseThrow(EntityNotFoundException::new).getName(),
        getArmorByType(playerCharacter.getArmors(), ArmorType.HEAD),
        getArmorByType(playerCharacter.getArmors(), ArmorType.TORSO),
        getArmorByType(playerCharacter.getArmors(), ArmorType.LEGS),
        getArmorByType(playerCharacter.getArmors(), ArmorType.HANDS),
        getArmorByType(playerCharacter.getArmors(), ArmorType.FEET));
  }

  private static String getArmorByType(Set<Armor> armorList, ArmorType type) {
    return armorList.stream()
        .filter(armor -> armor.getArmorType() == type)
        .findFirst()
        .map(Armor::getName)
        .orElse(null);
  }
}
