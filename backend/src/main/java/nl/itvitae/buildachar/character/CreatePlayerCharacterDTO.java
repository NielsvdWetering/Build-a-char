package nl.itvitae.buildachar.character;

import java.util.List;
import java.util.Map;

public record CreatePlayerCharacterDTO(
    String id,
    String name,
    String description,
    String raceId,
    String classId,
    String weaponId,
    String toolId,
    List<String> armorIds,
    Map<Long, Byte> characterPicture) {}
