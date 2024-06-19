package nl.itvitae.buildachar.character;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
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
import nl.itvitae.buildachar.user.User;
import nl.itvitae.buildachar.weapon.Weapon;
import nl.itvitae.buildachar.weapon.WeaponService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(ControllerRoutes.CHARACTER_ROUTE)
@CrossOrigin(origins = {"${app-cors}"})
@AllArgsConstructor
public class PlayerCharacterController {
  private final Logger logger = LoggerFactory.getLogger(PlayerCharacterController.class);

  private final PlayerCharacterService playerCharacterService;
  private final RaceService raceService;
  private final CharacterClassService characterClassService;
  private final WeaponService weaponService;
  private final ToolService toolService;
  private final ArmorService armorService;

  @PostMapping
  public ResponseEntity<CreatedCharacterDTO> create(
      @RequestBody CreatePlayerCharacterDTO characterDTO,
      UriComponentsBuilder ucb,
      Authentication authentication) { // Authentication details of the current user
    User user = (User) authentication.getPrincipal(); // Retrieves the current user
    Result<PlayerCharacter> saveCharacterResult =
        playerCharacterService.save(getNewCharacterValues(characterDTO), user);

    if (!saveCharacterResult.succeeded()) {
      throw new RestException(HttpStatus.BAD_REQUEST, saveCharacterResult.error());
    }

    URI uri =
        ucb.path(ControllerRoutes.CHARACTER_ROUTE + "/{id}")
            .build(saveCharacterResult.body().getId());
    return ResponseEntity.created(uri)
        .body(new CreatedCharacterDTO(saveCharacterResult.body().getId().toString()));
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
  public ResponseEntity<List<PlayerCharacterDetailsDTO>> getAll(
      @RequestParam(required = false) List<String> race,
      @RequestParam(required = false, name = "class") List<String> characterClass) {
    List<PlayerCharacter> filteredCharacters = new ArrayList<>();
    if (race == null && characterClass == null) {
      List<PlayerCharacter> playerCharacters = playerCharacterService.getAll();
      if (playerCharacters.isEmpty()) {
        return ResponseEntity.notFound().build();
      } else {
        return ResponseEntity.ok(
            playerCharacters.stream().map(PlayerCharacterDetailsDTO::from).toList());
      }
    }
    if (race != null && !race.isEmpty() && characterClass != null && !characterClass.isEmpty()) {
      List<Race> convertedRaces = raceService.getByName(race);
      List<CharacterClass> convertedClasses = characterClassService.getByName(characterClass);
      List<PlayerCharacter> filtered =
          playerCharacterService.getByClassAndRace(convertedRaces, convertedClasses);
      return ResponseEntity.ok(filtered.stream().map(PlayerCharacterDetailsDTO::from).toList());
    }

    if (race != null && !race.isEmpty() && characterClass == null) {
      raceService
          .getByName(race)
          .forEach(name -> filteredCharacters.addAll(playerCharacterService.getByRaceNames(name)));
    }
    if (characterClass != null && !characterClass.isEmpty() && race == null) {
      characterClassService
          .getByName(characterClass)
          .forEach(
              name ->
                  filteredCharacters.addAll(playerCharacterService.getByCharacterClassNames(name)));
    }

    return ResponseEntity.ok(
        filteredCharacters.stream().map(PlayerCharacterDetailsDTO::from).toList());
  }

  @PatchMapping("/{id}")
  public ResponseEntity<PlayerCharacter> patch(
      @PathVariable UUID id, @RequestBody PlayerCharacterPatchDTO playerCharacterPatchDTO) {
    return ResponseEntity.ok(playerCharacterService.patch(id, playerCharacterPatchDTO));
  }

  @GetMapping("/{id}")
  public ResponseEntity<PlayerCharacterGetDTO> getById(@PathVariable UUID id) {
    return playerCharacterService
        .getById(id)
        .map(PlayerCharacterGetDTO::from)
        .map(ResponseEntity::ok)
        .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND));
  }
}
