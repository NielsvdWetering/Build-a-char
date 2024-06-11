package nl.itvitae.buildachar.characterclass;


public record CharacterClassDTO(String id, String name) {

  static CharacterClassDTO from(CharacterClass characterClass) {
    return new CharacterClassDTO(characterClass.getId().toString(), characterClass.getName());
  }
}
