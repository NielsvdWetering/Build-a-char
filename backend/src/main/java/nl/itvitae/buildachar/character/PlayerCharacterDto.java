package nl.itvitae.buildachar.character;

import java.util.UUID;
import nl.itvitae.buildachar.race.RaceAttributes;

public record PlayerCharacterDto(
    UUID id,
    String name,
    String description,
    String characterClass,
    String race,
    RaceAttributes raceAttributes,
    String tool,
    String weapon,
    String armorHead,
    String armorTorso,
    String armorLegs,
    String armorHands,
    String armorFeet) {}
