package nl.itvitae.buildachar.character;

import static nl.itvitae.buildachar.armor.ArmorType.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import nl.itvitae.buildachar.characterclass.CharacterClassService;
import nl.itvitae.buildachar.helpers.Result;
import nl.itvitae.buildachar.race.RaceService;
import nl.itvitae.buildachar.tool.ToolService;
import nl.itvitae.buildachar.weapon.WeaponService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerCharacterService {
  private final PlayerCharacterRepository playerCharacterRepository;
  private final RaceService raceService;
  private final CharacterClassService characterClassService;
  private final WeaponService weaponService;
  private final ToolService toolService;

  public List<PlayerCharacter> getAll() {
    return playerCharacterRepository.findAll();
  }

  public Optional<PlayerCharacter> getById(UUID id) {
    return playerCharacterRepository.findById(id);
  }

  public Result<PlayerCharacter> save(NewCharacterValues values) {
    if (values.name() == null || values.name().isBlank()) {
      return Result.errorResult("name is required");
    }

    PlayerCharacter newPlayerCharacter = new PlayerCharacter(values.name(), values.description());

    if (values.race() != null) {
      newPlayerCharacter.setRace(values.race());
    }

    if (values.characterClass() != null) {
      newPlayerCharacter.setCharacterClass(values.characterClass());
    }

    if (values.weapon() != null) {
      newPlayerCharacter.getWeapons().add(values.weapon());
    }

    if (values.tool() != null) {
      newPlayerCharacter.getTools().add(values.tool());
    }

    if (values.armors() != null && !values.armors().isEmpty()) {
      values.armors().forEach(armor -> newPlayerCharacter.getArmors().add(armor));
    }

    return Result.succesResult(playerCharacterRepository.save(newPlayerCharacter));
  }

  public PlayerCharacter update(PlayerCharacter playerCharacter) {
    return playerCharacterRepository.save(playerCharacter);
  }
}
