package nl.itvitae.buildachar.character;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import nl.itvitae.buildachar.armor.Armor;
import nl.itvitae.buildachar.race.Stats;
import nl.itvitae.buildachar.tool.ToolDTO;
import nl.itvitae.buildachar.weapon.WeaponDTO;

public record PlayerCharacterGetDTO(
    UUID id,
    String name,
    String description,
    Stats stats,
    Set<Armor> armorList,
    List<WeaponDTO> weapons,
    List<ToolDTO> tools) {

  static PlayerCharacterGetDTO from(PlayerCharacter playerCharacter) {
    return new PlayerCharacterGetDTO(
        playerCharacter.getId(),
        playerCharacter.getName(),
        playerCharacter.getDescription(),
        playerCharacter.getRace().getStats(),
        playerCharacter.getArmors(),
        List.of(WeaponDTO.from(playerCharacter.getWeapon())),
        List.of(ToolDTO.from(playerCharacter.getTool())));
  }
}
