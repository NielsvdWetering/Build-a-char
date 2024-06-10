package nl.itvitae.buildachar.character;

import static nl.itvitae.buildachar.armor.ArmorType.*;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import nl.itvitae.buildachar.armor.Armor;
import nl.itvitae.buildachar.armor.ArmorRepository;
import nl.itvitae.buildachar.characterclass.CharacterClass;
import nl.itvitae.buildachar.characterclass.CharacterClassRepository;
import nl.itvitae.buildachar.race.Race;
import nl.itvitae.buildachar.race.RaceRepository;
import nl.itvitae.buildachar.tool.Tool;
import nl.itvitae.buildachar.tool.ToolRepository;
import nl.itvitae.buildachar.weapon.Weapon;
import nl.itvitae.buildachar.weapon.WeaponRepository;
import org.springframework.stereotype.Service;

@Service
public class PlayerCharacterService {
  private PlayerCharacterRepository playerCharacterRepository;
  private ArmorRepository armorRepository;
  private WeaponRepository weaponRepository;
  private ToolRepository toolRepository;
  private CharacterClassRepository characterClassRepository;
  private RaceRepository raceRepository;

  public PlayerCharacterService(
      PlayerCharacterRepository playerCharacterRepository,
      ArmorRepository armorRepository,
      WeaponRepository weaponRepository,
      ToolRepository toolRepository,
      CharacterClassRepository characterClassRepository,
      RaceRepository raceRepository) {
    this.playerCharacterRepository = playerCharacterRepository;
    this.armorRepository = armorRepository;
    this.weaponRepository = weaponRepository;
    this.toolRepository = toolRepository;
    this.characterClassRepository = characterClassRepository;
    this.raceRepository = raceRepository;
  }

  public List<PlayerCharacter> getAll() {
    return playerCharacterRepository.findAll();
  }

  public Optional<PlayerCharacter> getById(UUID id) {
    return playerCharacterRepository.findById(id);
  }

  public PlayerCharacter save(String name, String description) {
    return playerCharacterRepository.save(new PlayerCharacter(name, description));
  }

  public PlayerCharacter patch(UUID id, PlayerCharacterPatchDTO playerCharacterPatchDTO) {
    if (playerCharacterRepository.findById(id).isEmpty()) throw new EntityNotFoundException();
    PlayerCharacter existingPlayerCharacter = playerCharacterRepository.findById(id).get();

    if (playerCharacterPatchDTO.name() != null)
      existingPlayerCharacter.setName(playerCharacterPatchDTO.name());

    if (playerCharacterPatchDTO.description() != null)
      existingPlayerCharacter.setDescription(playerCharacterPatchDTO.description());

    if (playerCharacterPatchDTO.race() != null) {
      Optional<Race> optionalRace =
          raceRepository.findById(UUID.fromString(playerCharacterPatchDTO.race()));
      optionalRace.ifPresent(existingPlayerCharacter::setRace);
    }

    if (playerCharacterPatchDTO.characterClass() != null) {
      Optional<CharacterClass> optionalClass =
          characterClassRepository.findById(
              UUID.fromString(playerCharacterPatchDTO.characterClass()));
      optionalClass.ifPresent(existingPlayerCharacter::setCharacterClass);
    }

    if (playerCharacterPatchDTO.weapon() != null) {
      Optional<Weapon> optionalWeapon =
          weaponRepository.findById(UUID.fromString(playerCharacterPatchDTO.weapon()));
      optionalWeapon.ifPresent(existingPlayerCharacter::setWeapon);
    }
    if (playerCharacterPatchDTO.armors() != null) {
      Set<Armor> newArmorList =
          playerCharacterPatchDTO.armors().stream()
              .map(armor -> armorRepository.findById(UUID.fromString(armor)).orElseThrow())
              .collect(Collectors.toSet());
      existingPlayerCharacter.setArmors(newArmorList);
    }

    if (playerCharacterPatchDTO.tool() != null) {
      Optional<Tool> optionalTool =
          toolRepository.findById(UUID.fromString(playerCharacterPatchDTO.tool()));
      optionalTool.ifPresent(existingPlayerCharacter::setTool);
    }
    playerCharacterRepository.save(existingPlayerCharacter);
    return existingPlayerCharacter;
  }

  public PlayerCharacter update(PlayerCharacter newCharacter) {
    return playerCharacterRepository.save(newCharacter);
  }
}
