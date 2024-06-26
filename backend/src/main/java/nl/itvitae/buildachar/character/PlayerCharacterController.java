package nl.itvitae.buildachar.character;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;
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
        getArmorsFromId(characterDTO.armorIds()),
        playerCharacterService.toByteArray(characterDTO.characterPicture().values()));
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

  @PatchMapping("/{id}")
  public ResponseEntity<PlayerCharacter> patch(
      @PathVariable UUID id,
      @RequestBody PlayerCharacterPatchDTO playerCharacterPatchDTO,
      Authentication authentication) {
    User user = (User) authentication.getPrincipal();
    PlayerCharacter playerCharacter =
        playerCharacterService
            .getById(id)
            .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND));

    if (!playerCharacter.getUser().getId().equals(user.getId())) {
      throw new RestException(HttpStatus.FORBIDDEN, "You can only edit characters you own");
    }
    return ResponseEntity.ok(playerCharacterService.patch(id, playerCharacterPatchDTO));
  }

  @GetMapping("/{id}")
  public ResponseEntity<PlayerCharacterGetDTO> getById(
      @PathVariable UUID id, Authentication authentication) {
    Optional<User> user =
        Optional.ofNullable(authentication).map(auth -> (User) auth.getPrincipal());
    PlayerCharacter character =
        playerCharacterService
            .getById(id)
            .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND));
    boolean isOwned =
        user.map(value -> value.getId().equals(character.getUser().getId())).orElse(false);
    return ResponseEntity.ok(PlayerCharacterGetDTO.from(character, isOwned));
  }

  @GetMapping()
  public ResponseEntity<Set<PlayerCharacterDetailsDTO>> getAll(
      @RequestParam(required = false, name = "race") Set<String> races,
      @RequestParam(required = false, name = "class") Set<String> characterClasses,
      @RequestParam(required = false) String search,
      @RequestParam(required = false) boolean ownedOnly,
      Authentication authentication) {

    if (ownedOnly && (authentication == null)) {
      throw new RestException(
          HttpStatus.UNAUTHORIZED, "must be authorized te view owned characters");
    }

    User user = ownedOnly ? ((User) authentication.getPrincipal()) : null;

    Set<PlayerCharacter> result =
        playerCharacterService.getCharactersDynamic(races, characterClasses, user, search);

    return ResponseEntity.ok(
        result.stream().map(PlayerCharacterDetailsDTO::from).collect(Collectors.toSet()));
  }

  // resources loading from https://mkyong.com/java/java-read-a-file-from-resources-folder/
  @GetMapping("/image/{imageId}")
  public @ResponseBody byte[] getImage(@PathVariable UUID imageId) throws IOException {

    // Define the directory where uploaded images are stored
    String uploadDir = "../characterImages/";

    // Construct the full file path
    File file = new File(uploadDir + imageId.toString());

    // Check if the file exists and is readable
    if (file.exists() && file.isFile()) {
      try (InputStream in = new FileInputStream(file)) {
        return in.readAllBytes();
      }
    } else {
      // Return an appropriate response if the file is not found
      return null;
    }
  }
}
