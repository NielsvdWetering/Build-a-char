package nl.itvitae.buildachar.character;

import java.util.List;

public record PlayerCharacterPatchDTO(
    String name,
    String description,
    String raceId,
    String characterClassId,
    String weaponId,
    List<String> armorIds,
    String toolId) {}
