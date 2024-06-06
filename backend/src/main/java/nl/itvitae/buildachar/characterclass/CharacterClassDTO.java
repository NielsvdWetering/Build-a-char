package nl.itvitae.buildachar.characterclass;

import java.util.UUID;

public record CharacterClassDTO(UUID id, String name) {

  static CharacterClassDTO from(CharacterClass characterClass) {
    return new CharacterClassDTO(characterClass.getId(), characterClass.getName());
  }
}
