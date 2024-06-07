package nl.itvitae.buildachar.character;

import java.net.URI;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import nl.itvitae.buildachar.ControllerRoutes;
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
@RequiredArgsConstructor
@RequestMapping(ControllerRoutes.CHARACTER_ROUTE)
@CrossOrigin(origins = {"${app-cors}"})
public class PlayerCharacterController {
  private final PlayerCharacterService playerCharacterService;
  private final RaceService raceService;
  private final CharacterClassService characterClassService;
  private final WeaponService weaponService;
  private final ToolService toolService;

  @PostMapping
  public ResponseEntity<Void> create(
      @RequestBody CreatePlayerCharacterDTO characterDTO, UriComponentsBuilder ucb) {
    CreateCharacterValidationResult validationResult = getCreateCharacterDTOErrors(characterDTO);

    if (!validationResult.succeeded()) {
      throw new RestException(HttpStatus.BAD_REQUEST, String.join(";", validationResult.errors()));
    }

    Race race =
        validationResult
            .raceId()
            .map(
                raceId ->
                    raceService
                        .getById(raceId)
                        .orElseThrow(
                            () ->
                                new RestException(
                                    HttpStatus.BAD_REQUEST,
                                    "race with id '" + characterDTO.raceId() + "' not found")))
            .orElse(null);

    CharacterClass characterClass =
        validationResult
            .classId()
            .map(
                classId ->
                    characterClassService
                        .getById(classId)
                        .orElseThrow(
                            () ->
                                new RestException(
                                    HttpStatus.BAD_REQUEST,
                                    "characterClass with id '"
                                        + characterDTO.classId()
                                        + "' not found")))
            .orElse(null);

    Weapon weapon =
        validationResult
            .weaponId()
            .map(
                weaponId ->
                    weaponService
                        .getById(weaponId)
                        .orElseThrow(
                            () ->
                                new RestException(
                                    HttpStatus.BAD_REQUEST,
                                    "weapon with id '" + characterDTO.weaponId() + "' not found")))
            .orElse(null);

    Tool tool =
        validationResult
            .toolId()
            .map(
                toolId ->
                    toolService
                        .findById(toolId)
                        .orElseThrow(
                            () ->
                                new RestException(
                                    HttpStatus.BAD_REQUEST,
                                    "tool with id '" + characterDTO.toolId() + "' not found")))
            .orElse(null);

    Result<PlayerCharacter> saveCharacterResult =
        playerCharacterService.save(
            characterDTO.name(), characterDTO.description(), race, characterClass, weapon, tool);

    if (!saveCharacterResult.succeeded()) {
      throw new RestException(HttpStatus.BAD_REQUEST, saveCharacterResult.error());
    }

    URI uri = ucb.path("{id}").build(saveCharacterResult.body().getId());
    return ResponseEntity.created(uri).build();
  }

  private CreateCharacterValidationResult getCreateCharacterDTOErrors(
      CreatePlayerCharacterDTO characterDTO) {
    ArrayList<String> errors = new ArrayList<>();

    Result<Optional<UUID>> validateRaceResult = validateRace(characterDTO.raceId());
    Result<Optional<UUID>> validateClassResult = validateClass(characterDTO.classId());
    Result<Optional<UUID>> validateWeaponResult = validateWeapon(characterDTO.weaponId());
    Result<Optional<UUID>> validateToolResult = validateTool(characterDTO.toolId());

    if (!validateRaceResult.succeeded()) {
      errors.add(validateRaceResult.error());
    }
    if (!validateClassResult.succeeded()) {
      errors.add(validateClassResult.error());
    }
    if (!validateWeaponResult.succeeded()) {
      errors.add(validateWeaponResult.error());
    }
    if (!validateToolResult.succeeded()) {
      errors.add(validateToolResult.error());
    }

    return new CreateCharacterValidationResult(
        errors.isEmpty(),
        errors,
        validateRaceResult.body(),
        validateClassResult.body(),
        validateWeaponResult.body(),
        validateToolResult.body());
  }

  private Result<Optional<UUID>> validateRace(String raceId) {
    if (raceId == null) {
      return Result.succesResult(Optional.empty());
    }

    return UUIDHelper.tryParseUUID(raceId)
        .map(Optional::of)
        .map(Result::succesResult)
        .orElseGet(() -> Result.errorResult("raceId is not a valid UUID"));
  }

  private Result<Optional<UUID>> validateClass(String classId) {
    if (classId == null) {
      return Result.succesResult(Optional.empty());
    }

    return UUIDHelper.tryParseUUID(classId)
        .map(Optional::of)
        .map(Result::succesResult)
        .orElseGet(() -> Result.errorResult("classId is not a valid UUID"));
  }

  private Result<Optional<UUID>> validateWeapon(String weaponId) {
    if (weaponId == null) {
      return Result.succesResult(Optional.empty());
    }

    return UUIDHelper.tryParseUUID(weaponId)
        .map(Optional::of)
        .map(Result::succesResult)
        .orElseGet(() -> Result.errorResult("weaponId is not a valid UUID"));
  }

  private Result<Optional<UUID>> validateTool(String toolId) {
    if (toolId == null) {
      return Result.succesResult(Optional.empty());
    }

    return UUIDHelper.tryParseUUID(toolId)
        .map(Optional::of)
        .map(Result::succesResult)
        .orElseGet(() -> Result.errorResult("toolId is not a valid UUID"));
  }
}
