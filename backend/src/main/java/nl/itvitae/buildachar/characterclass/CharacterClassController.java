package nl.itvitae.buildachar.characterclass;

import lombok.RequiredArgsConstructor;
import nl.itvitae.buildachar.ControllerRoutes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ControllerRoutes.CLASS_ROUTE)
@RequiredArgsConstructor
@CrossOrigin(origins = {"${app-cors}"})
public class CharacterClassController {
  private final CharacterClassService characterClassService;

  @GetMapping("/getAll")
  public ResponseEntity<Iterable<CharacterClassDTO>> getAll() {
    Iterable<CharacterClassDTO> characterClassDTOS =
        characterClassService.getAll().stream().map(CharacterClassDTO::from).toList();
    return ResponseEntity.ok().body(characterClassDTOS);
  }
}
