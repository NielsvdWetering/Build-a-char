package nl.itvitae.buildachar.character;

import lombok.RequiredArgsConstructor;
import nl.itvitae.buildachar.ControllerRoutes;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(ControllerRoutes.CHARACTER_ROUTE)
@CrossOrigin(origins = {"${app-cors}"})
public class PlayerCharacterController {
  private final PlayerCharacterService playerCharacterService;
}
