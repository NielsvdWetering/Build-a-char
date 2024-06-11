package nl.itvitae.buildachar.character;

import java.util.UUID;

public record PlayerCharacterGetDTO(UUID id, String name, String description) {

  static PlayerCharacterGetDTO from(PlayerCharacter playerCharacter) {
    return new PlayerCharacterGetDTO(
        playerCharacter.getId(), playerCharacter.getName(), playerCharacter.getDescription());
  }
}
