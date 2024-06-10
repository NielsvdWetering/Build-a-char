package nl.itvitae.buildachar.character;

import java.util.List;
import java.util.UUID;
import nl.itvitae.buildachar.ControllerRoutes;
import nl.itvitae.buildachar.armor.Armor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ControllerRoutes.CHARACTER_ROUTE)
@CrossOrigin(origins = {"${app-cors}"})
public class PlayerCharacterController {
  private final PlayerCharacterService playerCharacterService;

  public PlayerCharacterController(PlayerCharacterService playerCharacterService) {
    this.playerCharacterService = playerCharacterService;
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
}
