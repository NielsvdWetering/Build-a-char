package nl.itvitae.buildachar.character;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import nl.itvitae.buildachar.ControllerRoutes;
import nl.itvitae.buildachar.armor.Armor;
import nl.itvitae.buildachar.armor.ArmorService;
import nl.itvitae.buildachar.characterclass.CharacterClass;
import nl.itvitae.buildachar.characterclass.CharacterClassService;
import nl.itvitae.buildachar.exceptions.RestException;
import nl.itvitae.buildachar.helpers.Result;
import nl.itvitae.buildachar.helpers.UUIDHelper;
import nl.itvitae.buildachar.race.Race;
import nl.itvitae.buildachar.race.RaceService;
import nl.itvitae.buildachar.tool.Tool;
import nl.itvitae.buildachar.tool.ToolService;
import nl.itvitae.buildachar.weapon.Weapon;
import nl.itvitae.buildachar.weapon.WeaponService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(ControllerRoutes.CHARACTER_ROUTE)
@CrossOrigin(origins = {"${app-cors}"})
@AllArgsConstructor
public class PlayerCharacterController {
  private final PlayerCharacterService playerCharacterService;
  private final RaceService raceService;
  private final CharacterClassService characterClassService;
  private final WeaponService weaponService;
  private final ToolService toolService;
  private final ArmorService armorService;

  @PostMapping
  public ResponseEntity<Void> create(
      @RequestBody CreatePlayerCharacterDTO characterDTO, UriComponentsBuilder ucb) {
    Result<PlayerCharacter> saveCharacterResult =
        playerCharacterService.save(getNewCharacterValues(characterDTO));

    if (!saveCharacterResult.succeeded()) {
      throw new RestException(HttpStatus.BAD_REQUEST, saveCharacterResult.error());
    }

    URI uri =
        ucb.path(ControllerRoutes.CHARACTER_ROUTE + "/{id}")
            .build(saveCharacterResult.body().getId());
    return ResponseEntity.created(uri).build();
  }

  private NewCharacterValues getNewCharacterValues(CreatePlayerCharacterDTO characterDTO) {
    if (characterDTO.name() == null || characterDTO.name().isBlank()) {
      throw new RestException(HttpStatus.BAD_REQUEST, "name is required");
    }

    return new NewCharacterValues(
        characterDTO.name(),
        characterDTO.description(),
        getRaceFromId(characterDTO.raceId()),
        getClassFromId(characterDTO.classId()),
        getWeaponFromId(characterDTO.weaponId()),
        getToolFromId(characterDTO.toolId()),
        getArmorsFromId(characterDTO.armorIds()));
  }

  private Race getRaceFromId(String raceId) {
    if (raceId == null) {
      return null;
    }

    return UUIDHelper.tryParseUUID(raceId)
        .flatMap(raceService::getById)
        .orElseThrow(() -> new RestException(HttpStatus.BAD_REQUEST, "raceId is invalid"));
  }

  private CharacterClass getClassFromId(String classId) {
    if (classId == null) {
      return null;
    }

    return UUIDHelper.tryParseUUID(classId)
        .flatMap(characterClassService::getById)
        .orElseThrow(() -> new RestException(HttpStatus.BAD_REQUEST, "classId is invalid"));
  }

  private Weapon getWeaponFromId(String weaponId) {
    if (weaponId == null) {
      return null;
    }

    return UUIDHelper.tryParseUUID(weaponId)
        .flatMap(weaponService::getById)
        .orElseThrow(() -> new RestException(HttpStatus.BAD_REQUEST, "weaponId is invalid"));
  }

  private Tool getToolFromId(String toolId) {
    if (toolId == null) {
      return null;
    }

    return UUIDHelper.tryParseUUID(toolId)
        .flatMap(toolService::findById)
        .orElseThrow(() -> new RestException(HttpStatus.BAD_REQUEST, "toolId is invalid"));
  }

  private List<Armor> getArmorsFromId(List<String> armorIds) {
    if (armorIds == null) {
      return null;
    }

    List<String> invalidIds = new ArrayList<>();

    List<Armor> parsedArmors = new ArrayList<>();
    for (String armorId : armorIds) {
      UUIDHelper.tryParseUUID(armorId)
          .flatMap(armorService::getById)
          .ifPresentOrElse(parsedArmors::add, () -> invalidIds.add(armorId));
    }

    if (!invalidIds.isEmpty()) {
      throw new RestException(
          HttpStatus.BAD_REQUEST, "armorIds [" + String.join(", ", invalidIds) + "] are invalid");
    }

    return parsedArmors;
  }

  @GetMapping
  public ResponseEntity<List<PlayerCharacterPostDTO>> getAll() {
    List<PlayerCharacter> playerCharacters = playerCharacterService.getAll();
    if (playerCharacters.isEmpty()) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok(toCharacterDto(playerCharacters));
    }
  }

  public List<PlayerCharacterPostDTO> toCharacterDto(List<PlayerCharacter> playerCharacters) {
    return playerCharacters.stream().map(this::convertToDto).toList();
  }

  private PlayerCharacterPostDTO convertToDto(PlayerCharacter playerCharacter) {
    String armorHead = null;
    String armorTorso = null;
    String armorLegs = null;
    String armorHands = null;
    String armorFeet = null;

    for (Armor armor : playerCharacter.getArmors()) {
      switch (armor.getArmorType()) {
        case HEAD -> armorHead = armor.getName();
        case TORSO -> armorTorso = armor.getName();
        case LEGS -> armorLegs = armor.getName();
        case HANDS -> armorHands = armor.getName();
        case FEET -> armorFeet = armor.getName();
      }
    }

    return new PlayerCharacterPostDTO(
        playerCharacter.getId(),
        playerCharacter.getName(),
        playerCharacter.getDescription(),
        playerCharacter.getCharacterClass() == null
            ? "unknown"
            : playerCharacter.getCharacterClass().getName(),
        playerCharacter.getRace() == null ? "unknown" : playerCharacter.getRace().getName(),
        playerCharacter.getRace() != null ? playerCharacter.getRace().getStats() : null,
        playerCharacter.getTool() == null ? "unknown" : playerCharacter.getTool().getName(),
        playerCharacter.getWeapon() == null ? "unknown" : playerCharacter.getWeapon().getName(),
        armorHead,
        armorTorso,
        armorLegs,
        armorHands,
        armorFeet);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<PlayerCharacter> patch(
      @PathVariable UUID id, @RequestBody PlayerCharacterPatchDTO playerCharacterPatchDTO) {
    return ResponseEntity.ok(playerCharacterService.patch(id, playerCharacterPatchDTO));
  }

  @GetMapping("/{id}")
  public ResponseEntity<PlayerCharacterGetDTO> getById(@PathVariable UUID id) {
    Optional<PlayerCharacter> possiblePlayer = playerCharacterService.getById(id);
    if (possiblePlayer.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(possiblePlayer.map(PlayerCharacterGetDTO::from).get());
  }
}
