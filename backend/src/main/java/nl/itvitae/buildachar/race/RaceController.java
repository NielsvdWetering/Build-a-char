package nl.itvitae.buildachar.race;

import java.util.List;
import lombok.RequiredArgsConstructor;
import nl.itvitae.buildachar.ControllerRoutes;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(ControllerRoutes.RACE_ROUTE)
@CrossOrigin(origins = {"${app-cors}"})
public class RaceController {
    private final RaceService raceService;

    @GetMapping
    public List<BasicRaceDTO> getAll(){
        return raceService.getAll().stream().map(BasicRaceDTO::from).toList();
    }
}
