package nl.itvitae.buildachar.weapon;

import lombok.RequiredArgsConstructor;
import nl.itvitae.buildachar.ControllerRoutes;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(ControllerRoutes.WEAPON_ROUTE)
@CrossOrigin(origins = {"${app-cors}"})
public class WeaponController {}
