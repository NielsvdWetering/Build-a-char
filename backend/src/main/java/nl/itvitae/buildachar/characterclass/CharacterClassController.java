package nl.itvitae.buildachar.characterclass;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import nl.itvitae.buildachar.ControllerRoutes;
import org.springframework.web.bind.annotation.*;

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

  @GetMapping("/name")
  public Set<CharacterClass> getByName(@RequestParam Set<String> classes) {
    return characterClassService.getByName(classes);
  }
}
