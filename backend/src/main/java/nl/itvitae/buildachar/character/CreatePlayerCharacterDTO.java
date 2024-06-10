package nl.itvitae.buildachar.character;

import java.util.List;

public record CreatePlayerCharacterDTO(
    String id,
    String name,
    String description,
    String raceId,
    String classId,
    String weaponId,
    String toolId,
    List<String> armorIds) {}
