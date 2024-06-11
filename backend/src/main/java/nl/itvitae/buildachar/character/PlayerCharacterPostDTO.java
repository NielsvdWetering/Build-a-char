package nl.itvitae.buildachar.character;

import java.util.UUID;
import nl.itvitae.buildachar.race.Stats;

public record PlayerCharacterPostDTO(
    UUID id,
    String name,
    String description,
    String characterClass,
    String race,
    Stats stats,
    String tool,
    String weapon,
    String armorHead,
    String armorTorso,
    String armorLegs,
    String armorHands,
    String armorFeet) {}
