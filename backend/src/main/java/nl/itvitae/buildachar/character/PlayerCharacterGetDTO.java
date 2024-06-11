package nl.itvitae.buildachar.character;

import java.util.UUID;
import nl.itvitae.buildachar.race.Stats;

public record PlayerCharacterGetDTO(UUID id, String name, String description, Stats stats) {

  static PlayerCharacterGetDTO from(PlayerCharacter playerCharacter) {
    return new PlayerCharacterGetDTO(
        playerCharacter.getId(), playerCharacter.getName(), playerCharacter.getDescription(),playerCharacter.getRace().getStats());
  }
}
