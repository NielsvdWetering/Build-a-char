package nl.itvitae.buildachar.character;

import static nl.itvitae.buildachar.armor.ArmorType.*;

import jakarta.persistence.EntityNotFoundException;
import java.io.*;
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

    // it checks if the file really contains something, because if not the frontend sends null, but
    // null is still some bytes
    if (values.characterPicture() != null && values.characterPicture().length > 50) {
      UUID pictureId = UUID.randomUUID();
      File newPicture = new File("../characterImages/" + pictureId);

      try {
        newPicture.createNewFile();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }

      try (FileOutputStream fos = new FileOutputStream(newPicture)) {
        fos.write(values.characterPicture());
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      newPlayerCharacter.setCharacterPicture(pictureId);
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

  public Set<PlayerCharacter> getCharactersDynamic(
      Set<String> raceNames, Set<String> classNames, User user, String name) {

    Specification<PlayerCharacter> spec = Specification.where(null);
    if (raceNames != null && !raceNames.isEmpty()) {
      Set<Race> races =
          raceNames.stream()
              .map(
                  raceName ->
                      raceRepository
                          .findByNameIgnoreCase(raceName)
                          .orElseThrow(EntityNotFoundException::new))
              .collect(Collectors.toSet());
      if (!races.isEmpty()) {
        spec = spec.and(PlayerCharacterSpecification.hasRaceIn(races));
      }
    }

    if (classNames != null && !classNames.isEmpty()) {
      Set<CharacterClass> characterClasses =
          classNames.stream()
              .map(
                  className ->
                      characterClassRepository
                          .findByNameIgnoreCase(className)
                          .orElseThrow(EntityNotFoundException::new))
              .collect(Collectors.toSet());

      if (!characterClasses.isEmpty()) {
        spec = spec.and(PlayerCharacterSpecification.hasClassIn(characterClasses));
      }
    }

    if (user != null) {
      spec = spec.and(PlayerCharacterSpecification.hasUserId(user.getId()));
    }

    if (name != null && !name.isEmpty()) {
      spec = spec.and(PlayerCharacterSpecification.hasNameContainingIgnoreCase(name));
    }

    return new HashSet<>(playerCharacterRepository.findAll(spec));
  }

  public byte[] toByteArray(Collection<Byte> bytes) {
    byte[] convertedBytes = new byte[bytes.size()];

    int index = 0;
    for (Byte b : bytes) {
      convertedBytes[index] = b;
      index++;
    }

    return convertedBytes;
  }
}
