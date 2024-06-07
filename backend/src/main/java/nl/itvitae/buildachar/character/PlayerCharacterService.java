package nl.itvitae.buildachar.character;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import nl.itvitae.buildachar.characterclass.CharacterClass;
import nl.itvitae.buildachar.characterclass.CharacterClassService;
import nl.itvitae.buildachar.helpers.Result;
import nl.itvitae.buildachar.race.Race;
import nl.itvitae.buildachar.race.RaceService;
import nl.itvitae.buildachar.tool.Tool;
import nl.itvitae.buildachar.tool.ToolService;
import nl.itvitae.buildachar.weapon.Weapon;
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

  public Result<PlayerCharacter> save(
      String name,
      String description,
      Race race,
      CharacterClass characterClass,
      Weapon weapon,
      Tool tool) {
    PlayerCharacter newPlayerCharacter = new PlayerCharacter(name, description);

    if (name == null || name.isBlank()) {
      return Result.errorResult("name is required");
    }

    if (race != null) {
      race.addToPlayerCharacters(Set.of(newPlayerCharacter));
      raceService.update(race);
      newPlayerCharacter.setRace(race);
    }

    if (characterClass != null) {
      characterClass.getCharacters().add(newPlayerCharacter);
      characterClassService.update(characterClass);
      newPlayerCharacter.setCharacterClass(characterClass);
    }

    if (weapon != null) {
      newPlayerCharacter.getWeapons().add(weapon);
      weapon.getPlayerCharacters().add(newPlayerCharacter);
    }

    if (tool != null) {
      newPlayerCharacter.getTools().add(tool);
      tool.getPlayerCharacters().add(newPlayerCharacter);
    }

    return Result.succesResult(playerCharacterRepository.save(newPlayerCharacter));
  }
}
