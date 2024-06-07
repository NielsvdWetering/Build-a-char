package nl.itvitae.buildachar.character;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public record CreateCharacterValidationResult(
    boolean succeeded,
    List<String> errors,
    Optional<UUID> raceId,
    Optional<UUID> classId,
    Optional<UUID> weaponId,
    Optional<UUID> toolId) {}
