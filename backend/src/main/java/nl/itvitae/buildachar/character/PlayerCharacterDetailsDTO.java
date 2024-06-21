package nl.itvitae.buildachar.character;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import nl.itvitae.buildachar.armor.Armor;
import nl.itvitae.buildachar.armor.ArmorType;
import nl.itvitae.buildachar.race.Stats;
import nl.itvitae.buildachar.tool.Tool;
import nl.itvitae.buildachar.weapon.Weapon;

public record PlayerCharacterDetailsDTO(
    UUID id,
    String name,
    String user,
    String description,
    String characterClass,
    String race,
    Stats stats,
    String tool,
    String weapon,
    String armorHead,
    String armorTorso,
    String armorHands,
    String armorLegs,
    String armorFeet,
    String characterPictureId) {
  static PlayerCharacterDetailsDTO from(PlayerCharacter playerCharacter) {
    Optional<Weapon> weapon = playerCharacter.getWeapon();
    Optional<Tool> tool = playerCharacter.getTool();

    String characterPicture =
        (playerCharacter.getCharacterPicture() != null)
            ? playerCharacter.getCharacterPicture().toString()
            : null;

    return new PlayerCharacterDetailsDTO(
        playerCharacter.getId(),
        playerCharacter.getName(),
        playerCharacter.getUser().getUsername(),
        playerCharacter.getDescription(),
        playerCharacter.getCharacterClass().getName(),
        playerCharacter.getRace().getName(),
        playerCharacter.getRace().getStats(),
        tool.map(Tool::getName).orElse(null),
        weapon.map(Weapon::getName).orElse(null),
        getArmorByType(playerCharacter.getArmors(), ArmorType.HEAD),
        getArmorByType(playerCharacter.getArmors(), ArmorType.TORSO),
        getArmorByType(playerCharacter.getArmors(), ArmorType.LEGS),
        getArmorByType(playerCharacter.getArmors(), ArmorType.HANDS),
        getArmorByType(playerCharacter.getArmors(), ArmorType.FEET),
        characterPicture);
  }

  private static String getArmorByType(Set<Armor> armorList, ArmorType type) {
    return armorList.stream()
        .filter(armor -> armor.getArmorType() == type)
        .findFirst()
        .map(Armor::getName)
        .orElse(null);
  }
}
