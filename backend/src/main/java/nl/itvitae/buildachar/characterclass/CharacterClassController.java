package nl.itvitae.buildachar.characterclass;

import lombok.RequiredArgsConstructor;
import nl.itvitae.buildachar.ControllerRoutes;
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

  @GetMapping
  public Iterable<CharacterClassDTO> getAll() {
    return characterClassService.getAll().stream().map(CharacterClassDTO::from).toList();
  }
}
