package nl.itvitae.buildachar.race;

import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import nl.itvitae.buildachar.ControllerRoutes;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ControllerRoutes.RACE_ROUTE)
@CrossOrigin(origins = {"${app-cors}"})
public class RaceController {
  private final RaceService raceService;

  @GetMapping
  public List<BasicRaceDTO> getAll() {
    return raceService.getAll().stream().map(BasicRaceDTO::from).toList();
  }

  // query param = GET ?races=Gnome,elf
  @GetMapping("/name")
  public Set<Race> getByName(@RequestParam Set<String> races) {
    return raceService.getByName(races);
  }
}
