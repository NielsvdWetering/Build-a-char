package nl.itvitae.buildachar.character;

import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import nl.itvitae.buildachar.ControllerRoutes;
import nl.itvitae.buildachar.armor.Armor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(ControllerRoutes.CHARACTER_ROUTE)
@CrossOrigin(origins = {"${app-cors}"})
public class PlayerCharacterController {
  @NonNull private final PlayerCharacterService playerCharacterService;

  @GetMapping
  public ResponseEntity<List<PlayerCharacterDto>> getAll() {
    List<PlayerCharacter> playerCharacters = playerCharacterService.getAll();
    if (playerCharacters.isEmpty()) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok(toCharacterDto(playerCharacters));
    }
  }

  public List<PlayerCharacterDto> toCharacterDto(List<PlayerCharacter> playerCharacters) {
    return playerCharacters.stream().map(this::convertToDto).toList();
  }

  private PlayerCharacterDto convertToDto(PlayerCharacter playerCharacter) {
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

    return new PlayerCharacterDto(
        playerCharacter.getId(),
        playerCharacter.getName() == null ? "unknown" : playerCharacter.getName(),
        playerCharacter.getDescription() == null ? "unknown" : playerCharacter.getDescription(),
        playerCharacter.getCharacterClass() == null
            ? "unknown"
            : playerCharacter.getCharacterClass().getName(),
        playerCharacter.getRace() == null ? "unknown" : playerCharacter.getRace().getName(),
        playerCharacter.getRace() != null ? playerCharacter.getRace().getRaceStats() : null,
        playerCharacter.getTool() == null ? "unknown" : playerCharacter.getTool().getName(),
        playerCharacter.getWeapon() == null ? "unknown" : playerCharacter.getWeapon().getName(),
        armorHead,
        armorTorso,
        armorLegs,
        armorHands,
        armorFeet);
  }
}
