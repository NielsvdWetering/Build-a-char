package nl.itvitae.buildachar.character;

import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import nl.itvitae.buildachar.ControllerRoutes;
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
    List<PlayerCharacterDto> playerCharacters = playerCharacterService.getAll();
    if (playerCharacters.isEmpty()) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok(playerCharacters);
    }
  }
}
