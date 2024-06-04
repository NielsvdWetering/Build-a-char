package nl.itvitae.buildachar;

import lombok.RequiredArgsConstructor;
import nl.itvitae.buildachar.armor.ArmorClass;
import nl.itvitae.buildachar.armor.ArmorService;
import nl.itvitae.buildachar.armor.ArmorType;
import nl.itvitae.buildachar.characterclass.CharacterClassService;
import nl.itvitae.buildachar.race.RaceService;
import nl.itvitae.buildachar.tool.ToolService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Seeder implements CommandLineRunner {
  private final RaceService raceService;
  private final CharacterClassService characterClassService;
  private final ToolService toolService;
  private final ArmorService armorService;

  @Override
  public void run(String... args) throws Exception {
    seedClasses();
    seedArmors();
  }

  private void seedClasses() {
    if (!characterClassService.getAll().isEmpty()) {
      return;
    }

    characterClassService.save("Warrior");
    characterClassService.save("Paladin");
    characterClassService.save("Wizard");
    characterClassService.save("Cleric");
    characterClassService.save("Bard");
    characterClassService.save("Rogue");
  }

  private void seedArmors() {
    if (!armorService.getAll().isEmpty()) {
      return;
    }

    armorService.save("Steel Helmet", "", 4.2, ArmorType.HEAD, ArmorClass.HEAVY);
    armorService.save("Steel Chestplate", "", 4.2, ArmorType.TORSO, ArmorClass.HEAVY);
    armorService.save("Steel Leggins", "", 4.2, ArmorType.LEGS, ArmorClass.HEAVY);
    armorService.save("Steel Gauntlets", "", 4.2, ArmorType.HANDS, ArmorClass.HEAVY);
    armorService.save("Steel Boots", "", 4.2, ArmorType.FEET, ArmorClass.HEAVY);

    armorService.save("Leather Cap", "", 4.2, ArmorType.HEAD, ArmorClass.MEDIUM);
    armorService.save("Leather Coat", "", 4.2, ArmorType.TORSO, ArmorClass.MEDIUM);
    armorService.save("Leather Pants", "", 4.2, ArmorType.LEGS, ArmorClass.MEDIUM);
    armorService.save("Leather Gloves", "", 4.2, ArmorType.HANDS, ArmorClass.MEDIUM);
    armorService.save("Leather Boots", "", 4.2, ArmorType.FEET, ArmorClass.MEDIUM);

    armorService.save("Cloth Hood", "", 4.2, ArmorType.HEAD, ArmorClass.LIGHT);
    armorService.save("Light Coat", "", 4.2, ArmorType.TORSO, ArmorClass.LIGHT);
    armorService.save("Light Pants", "", 4.2, ArmorType.LEGS, ArmorClass.LIGHT);
    armorService.save("Thin Gloves", "", 4.2, ArmorType.HANDS, ArmorClass.LIGHT);
    armorService.save("Worn Boots", "", 4.2, ArmorType.FEET, ArmorClass.LIGHT);
  }
}
