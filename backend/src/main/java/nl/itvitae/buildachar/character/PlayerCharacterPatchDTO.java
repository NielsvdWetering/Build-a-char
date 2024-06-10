package nl.itvitae.buildachar.character;

import java.util.List;

public record PlayerCharacterPatchDTO(
    String name,
    String description,
    String race,
    String characterClass,
    String weapon,
    List<String> armor,
    String tool) {}
