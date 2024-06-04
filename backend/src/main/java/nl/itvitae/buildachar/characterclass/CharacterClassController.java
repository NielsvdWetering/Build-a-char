package nl.itvitae.buildachar.characterclass;

import lombok.RequiredArgsConstructor;
import nl.itvitae.buildachar.ControllerRoutes;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ControllerRoutes.CLASS_ROUTE)
@RequiredArgsConstructor
@CrossOrigin(origins = {"${app-cors}"})
public class CharacterClassController {}
