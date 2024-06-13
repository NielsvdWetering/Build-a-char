package nl.itvitae.buildachar.character;

import java.util.List;
import java.util.UUID;
import nl.itvitae.buildachar.armor.ArmorDTO;
import nl.itvitae.buildachar.characterclass.CharacterClassDTO;
import nl.itvitae.buildachar.race.BasicRaceDTO;
import nl.itvitae.buildachar.race.Stats;

public record PlayerCharacterGetDTO(
    UUID id,
    String name,
    String description,
    Stats stats,
    List<ArmorDTO> armorList,
    BasicRaceDTO race,
    CharacterClassDTO characterClass) {

  static PlayerCharacterGetDTO from(PlayerCharacter playerCharacter) {
    return new PlayerCharacterGetDTO(
        playerCharacter.getId(),
        playerCharacter.getName(),
        playerCharacter.getDescription(),
        playerCharacter.getRace().getStats(),
        playerCharacter.getArmors().stream().map(ArmorDTO::from).toList(),
        BasicRaceDTO.from(playerCharacter.getRace()),
        CharacterClassDTO.from(playerCharacter.getCharacterClass()));
  }
}
