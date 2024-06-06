package nl.itvitae.buildachar.armor;

import java.util.List;

public record SortedArmorDTO(
    List<ArmorNoTypeDTO> headArmor,
    List<ArmorNoTypeDTO> torsoArmor,
    List<ArmorNoTypeDTO> legArmor,
    List<ArmorNoTypeDTO> handsArmor,
    List<ArmorNoTypeDTO> feetArmor) {

  static SortedArmorDTO from(List<Armor> armorList) {
    List<ArmorNoTypeDTO> headArmorList =
        armorList.stream()
            .filter(armor -> armor.getArmorType().equals(ArmorType.HEAD))
            .map(ArmorNoTypeDTO::from)
            .toList();
    List<ArmorNoTypeDTO> torsoArmorList =
        armorList.stream()
            .filter(armor -> armor.getArmorType().equals(ArmorType.TORSO))
            .map(ArmorNoTypeDTO::from)
            .toList();
    List<ArmorNoTypeDTO> legArmorList =
        armorList.stream()
            .filter(armor -> armor.getArmorType().equals(ArmorType.LEGS))
            .map(ArmorNoTypeDTO::from)
            .toList();
    List<ArmorNoTypeDTO> handArmorList =
        armorList.stream()
            .filter(armor -> armor.getArmorType().equals(ArmorType.HANDS))
            .map(ArmorNoTypeDTO::from)
            .toList();
    List<ArmorNoTypeDTO> feetArmorList =
        armorList.stream()
            .filter(armor -> armor.getArmorType().equals(ArmorType.FEET))
            .map(ArmorNoTypeDTO::from)
            .toList();
    return new SortedArmorDTO(
        headArmorList, torsoArmorList, legArmorList, handArmorList, feetArmorList);
  }
}
