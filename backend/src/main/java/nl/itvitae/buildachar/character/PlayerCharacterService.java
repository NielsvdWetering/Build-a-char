package nl.itvitae.buildachar.character;

import static nl.itvitae.buildachar.armor.ArmorType.*;

import jakarta.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import nl.itvitae.buildachar.armor.Armor;
import nl.itvitae.buildachar.armor.ArmorRepository;
import nl.itvitae.buildachar.characterclass.CharacterClass;
import nl.itvitae.buildachar.characterclass.CharacterClassRepository;
import nl.itvitae.buildachar.helpers.Result;
import nl.itvitae.buildachar.race.Race;
import nl.itvitae.buildachar.race.RaceRepository;
import nl.itvitae.buildachar.tool.Tool;
import nl.itvitae.buildachar.tool.ToolRepository;
import nl.itvitae.buildachar.user.User;
import nl.itvitae.buildachar.weapon.Weapon;
import nl.itvitae.buildachar.weapon.WeaponRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PlayerCharacterService {
  private final ArmorRepository armorRepository;
  private final WeaponRepository weaponRepository;
  private final ToolRepository toolRepository;
  private final CharacterClassRepository characterClassRepository;
  private final RaceRepository raceRepository;
  private final PlayerCharacterRepository playerCharacterRepository;

  public Set<PlayerCharacter> getAll() {
    return new HashSet<>(playerCharacterRepository.findAll());
  }

  public Set<PlayerCharacter> getByUser(User user) {
    if (user == null) {
      throw new RuntimeException("PlayerCharacterService.getByUser: user is null");
    }

    return new HashSet<>(
        playerCharacterRepository.findAll(PlayerCharacterSpecification.hasUserId(user.getId())));
  }

  public Optional<PlayerCharacter> getById(UUID id) {
    return playerCharacterRepository.findById(id);
  }

  public Result<PlayerCharacter> save(NewCharacterValues values, User user) {
    if (values.name() == null || values.name().isBlank()) {
      return Result.errorResult("name is required");
    }
    if (values.race() == null) {
      return Result.errorResult("race is required");
    }
    if (values.characterClass() == null) {
      return Result.errorResult("class is required");
    }

    PlayerCharacter newPlayerCharacter =
        new PlayerCharacter(
            values.name(), values.description(), values.race(), values.characterClass());

    if (values.weapon() != null) {
      newPlayerCharacter.setWeapon(values.weapon());
    }

    if (values.tool() != null) {
      newPlayerCharacter.setTool(values.tool());
    }

    if (values.armors() != null && !values.armors().isEmpty()) {
      values.armors().forEach(armor -> newPlayerCharacter.getArmors().add(armor));
    }

    if (user != null) {
      newPlayerCharacter.setUser(user);
    } else {
      throw new RuntimeException("PlayerCharacterService.save: The user is null");
    }

    return Result.succesResult(playerCharacterRepository.save(newPlayerCharacter));
  }

  public PlayerCharacter patch(UUID id, PlayerCharacterPatchDTO playerCharacterPatchDTO) {
    if (playerCharacterRepository.findById(id).isEmpty()) throw new EntityNotFoundException();
    PlayerCharacter existingPlayerCharacter = playerCharacterRepository.findById(id).get();

    if (playerCharacterPatchDTO.name() != null)
      existingPlayerCharacter.setName(playerCharacterPatchDTO.name());

    if (playerCharacterPatchDTO.description() != null)
      existingPlayerCharacter.setDescription(playerCharacterPatchDTO.description());

    if (playerCharacterPatchDTO.raceId() != null) {
      Optional<Race> optionalRace =
          raceRepository.findById(UUID.fromString(playerCharacterPatchDTO.raceId()));
      optionalRace.ifPresent(existingPlayerCharacter::setRace);
    }

    if (playerCharacterPatchDTO.characterClassId() != null) {
      Optional<CharacterClass> optionalClass =
          characterClassRepository.findById(
              UUID.fromString(playerCharacterPatchDTO.characterClassId()));
      optionalClass.ifPresent(existingPlayerCharacter::setCharacterClass);
    }

    if (playerCharacterPatchDTO.weaponId() != null) {
      Optional<Weapon> optionalWeapon =
          weaponRepository.findById(UUID.fromString(playerCharacterPatchDTO.weaponId()));
      optionalWeapon.ifPresent(existingPlayerCharacter::setWeapon);
    }
    if (playerCharacterPatchDTO.armorIds() != null) {
      Set<Armor> newArmorList =
          playerCharacterPatchDTO.armorIds().stream()
              .map(armor -> armorRepository.findById(UUID.fromString(armor)).orElseThrow())
              .collect(Collectors.toSet());
      existingPlayerCharacter.setArmors(newArmorList);
    }

    if (playerCharacterPatchDTO.toolId() != null) {
      Optional<Tool> optionalTool =
          toolRepository.findById(UUID.fromString(playerCharacterPatchDTO.toolId()));
      optionalTool.ifPresent(existingPlayerCharacter::setTool);
    }
    playerCharacterRepository.save(existingPlayerCharacter);
    return existingPlayerCharacter;
  }

  public PlayerCharacter update(PlayerCharacter newCharacter) {
    return playerCharacterRepository.save(newCharacter);
  }

  public Set<PlayerCharacter> getByRaceNames(Race raceNames) {
    return playerCharacterRepository.findByRace(raceNames);
  }

  public Set<PlayerCharacter> getByCharacterClassNames(CharacterClass characterClass) {
    return new HashSet<>(
        playerCharacterRepository.findAll(PlayerCharacterSpecification.hasClass(characterClass)));
  }

  public Set<PlayerCharacter> getByClassAndRace(Set<Race> races, Set<CharacterClass> classes) {
    return new HashSet<>(
        playerCharacterRepository.findAll(
            Specification.where(PlayerCharacterSpecification.hasRaceIn(races))
                .and(PlayerCharacterSpecification.hasClassIn(classes))));
  }

  public Set<PlayerCharacter> getByNameContaining(String param) {
    return playerCharacterRepository.findByNameContainingIgnoreCase(param);
  }

  public Set<PlayerCharacter> getCharactersDynamic(
      String raceName, String className, String name, UUID userId) {
    Specification<PlayerCharacter> spec =
        Specification.where(null); // soort van startpunt voor specification om op verder te bouwen

    if (raceName != null && !raceName.isEmpty()) {
      Race race = raceRepository.findByNameIgnoreCase(raceName);
      spec =
          spec.and(
              PlayerCharacterSpecification.hasRace(
                  race)); // de .and() zorgt ervoor dat je huidige spec(ificatie) word toegevoegd
      // aan de al bestaande specificaties. Het bouwt erop voort
    }
    if (className != null && !className.isEmpty()) {
      CharacterClass characterClass = characterClassRepository.findByNameIgnoreCase(className);
      spec = spec.and(PlayerCharacterSpecification.hasClass(characterClass));
    }
    if (name != null && !name.isEmpty()) {
      spec = spec.and(PlayerCharacterSpecification.hasNameContainingIgnoreCase(name));
    }

    if (userId != null) {
      spec = spec.and(PlayerCharacterSpecification.hasUserId(userId));
    }
    return new HashSet<>(playerCharacterRepository.findAll(spec));
  }
}
