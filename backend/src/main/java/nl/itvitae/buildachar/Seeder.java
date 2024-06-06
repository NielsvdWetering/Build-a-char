package nl.itvitae.buildachar;

import lombok.RequiredArgsConstructor;
import nl.itvitae.buildachar.armor.ArmorClass;
import nl.itvitae.buildachar.armor.ArmorService;
import nl.itvitae.buildachar.armor.ArmorType;
import nl.itvitae.buildachar.characterclass.CharacterClassService;
import nl.itvitae.buildachar.race.RaceService;
import nl.itvitae.buildachar.tool.ToolService;
import nl.itvitae.buildachar.weapon.WeaponService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Seeder implements CommandLineRunner {
  private final RaceService raceService;
  private final CharacterClassService characterClassService;
  private final ToolService toolService;
  private final ArmorService armorService;
  private final WeaponService weaponService;

  @Override
  public void run(String... args) throws Exception {
    seedClasses();
    seedArmors();
    seedTools();
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

  private void seedTools() {
    if (!toolService.getAll().isEmpty()) {
      return;
    }
    toolService.save("Hammer", "Tool used for driving nails into wood and other materials");
    toolService.save("Screwdriver", "Tool for driving screws with a handle and a shaft");
    toolService.save(
        "Wrench",
        "Tool used to provide grip and mechanical advantage in applying torque to turn objects");
    toolService.save(
        "Pliers",
        "Hand tool used to hold objects firmly, for bending, or for physical compression");
    toolService.save(
        "Drill",
        "Power tool used for making holes in various materials or fastening materials together with screws");
  }

  private void seedRace() {
    raceService.save("Dwarf", 3.2, 1.2, 4.1, 2., 2.8, 2.4);
    raceService.save("Elf", 2.8, 1.5, 4.0, 3.5, 3.0, 2.7);
    raceService.save("Orc", 3.5, 1.8, 3.2, 2.7, 2.9, 2.5);
    raceService.save("Human", 3.0, 1.7, 3.8, 3.0, 2.6, 2.8);
    raceService.save("Halfling", 2.4, 1.2, 4.3, 3.8, 2.3, 2.0);
    raceService.save("Gnome", 2.5, 1.3, 4.2, 3.9, 2.4, 2.1);
  }

  private void seedWeapons() {
    weaponService.save("sword", "weapon", "medium?", 45.);
    weaponService.save("axe", "weapon", "heavy", 60.);
    weaponService.save("dagger", "weapon", "light", 20.);
    weaponService.save("bow", "ranged", "medium", 35.);
    weaponService.save("spear", "weapon", "long", 50.);
    weaponService.save("mace", "weapon", "heavy", 55.);
  }
}
