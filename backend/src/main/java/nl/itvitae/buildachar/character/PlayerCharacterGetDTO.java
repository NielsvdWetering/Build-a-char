package nl.itvitae.buildachar.character;

import java.util.Set;
import java.util.UUID;
import nl.itvitae.buildachar.armor.Armor;
import nl.itvitae.buildachar.race.Stats;

public record PlayerCharacterGetDTO(UUID id, String name, String description, Stats stats, Set<Armor> armorList) {

  static PlayerCharacterGetDTO from(PlayerCharacter playerCharacter) {
    return new PlayerCharacterGetDTO(
        playerCharacter.getId(), playerCharacter.getName(), playerCharacter.getDescription(),playerCharacter.getRace().getStats(),playerCharacter.getArmors());
  }
}
