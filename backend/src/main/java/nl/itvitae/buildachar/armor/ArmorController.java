package nl.itvitae.buildachar.armor;

import lombok.RequiredArgsConstructor;
import nl.itvitae.buildachar.ControllerRoutes;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(ControllerRoutes.ARMOR_ROUTE)
@CrossOrigin(origins = {"${app-cors}"})
public class ArmorController {
  private final ArmorService armorService;

  @GetMapping("/sortedByType")
  public SortedArmorDTO getAllSortedByType() {
    return SortedArmorDTO.from(armorService.getAll());
  }
}
