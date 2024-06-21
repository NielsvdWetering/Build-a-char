package nl.itvitae.buildachar.race;

import java.util.List;
import lombok.RequiredArgsConstructor;
import nl.itvitae.buildachar.ControllerRoutes;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ControllerRoutes.RACE_ROUTE)
@CrossOrigin(origins = {"${app-cors}"})
public class RaceController {
  private final RaceService raceService;
  private final RaceRepository raceRepository;

  @GetMapping
  public List<BasicRaceDTO> getAll() {
    return raceService.getAll().stream().map(BasicRaceDTO::from).toList();
  }
}
