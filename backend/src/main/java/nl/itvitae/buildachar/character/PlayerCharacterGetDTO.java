package nl.itvitae.buildachar.character;

import java.util.List;
import java.util.UUID;
import nl.itvitae.buildachar.armor.ArmorDTO;
import nl.itvitae.buildachar.characterclass.CharacterClassDTO;
import nl.itvitae.buildachar.race.BasicRaceDTO;
import nl.itvitae.buildachar.race.Stats;
import nl.itvitae.buildachar.tool.ToolDTO;
import nl.itvitae.buildachar.weapon.WeaponDTO;

public record PlayerCharacterGetDTO(
    UUID id,
    String name,
    String description,
    Stats stats,
    List<WeaponDTO> weapons,
    List<ToolDTO> tools,
    List<ArmorDTO> armorList,
    BasicRaceDTO race,
    CharacterClassDTO characterClass) {

  static PlayerCharacterGetDTO from(PlayerCharacter playerCharacter) {
    return new PlayerCharacterGetDTO(
        playerCharacter.getId(),
        playerCharacter.getName(),
        playerCharacter.getDescription(),
        playerCharacter.getRace().getStats(),
        playerCharacter.getWeapon().map(WeaponDTO::from).map(List::of).orElse(null),
        playerCharacter.getTool().map(ToolDTO::from).map(List::of).orElse(null),
        playerCharacter.getArmors().stream().map(ArmorDTO::from).toList(),
        BasicRaceDTO.from(playerCharacter.getRace()),
        CharacterClassDTO.from(playerCharacter.getCharacterClass()));
  }
}
