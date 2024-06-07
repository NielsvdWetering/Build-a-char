package nl.itvitae.buildachar.character;

public record CreatePlayerCharacterDTO(
    String id,
    String name,
    String description,
    String raceId,
    String classId,
    String weaponId,
    String toolId) {}
