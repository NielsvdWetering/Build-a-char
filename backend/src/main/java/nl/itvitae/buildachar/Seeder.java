package nl.itvitae.buildachar;

import lombok.RequiredArgsConstructor;
import nl.itvitae.buildachar.armor.ArmorClass;
import nl.itvitae.buildachar.armor.ArmorService;
import nl.itvitae.buildachar.armor.ArmorType;
import nl.itvitae.buildachar.character.NewCharacterValues;
import nl.itvitae.buildachar.character.PlayerCharacter;
import nl.itvitae.buildachar.character.PlayerCharacterService;
import nl.itvitae.buildachar.characterclass.CharacterClass;
import nl.itvitae.buildachar.characterclass.CharacterClassService;
import nl.itvitae.buildachar.helpers.Result;
import nl.itvitae.buildachar.race.Race;
import nl.itvitae.buildachar.race.RaceService;
import nl.itvitae.buildachar.race.Stats;
import nl.itvitae.buildachar.tool.Tool;
import nl.itvitae.buildachar.tool.ToolService;
import nl.itvitae.buildachar.weapon.Weapon;
import nl.itvitae.buildachar.weapon.WeaponService;
import nl.itvitae.buildachar.weapon.WeaponType;
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
  private final PlayerCharacterService playerCharacterService;

  @Override
  public void run(String... args) throws Exception {
    seedClasses();
    seedArmors();
    seedTools();
    seedWeapons();
    seedRace();
    seedPlayerCharacters();
  }

  private void seedPlayerCharacters() {
    if (!playerCharacterService.getAll().isEmpty()) return;

    CharacterClass characterClass =
        characterClassService.getAll().stream().findFirst().orElseThrow();
    Weapon weapon = weaponService.getAll().stream().findFirst().orElseThrow();
    Tool tool = toolService.getAll().stream().findFirst().orElseThrow();
    Race race = raceService.getAll().stream().findFirst().orElseThrow();

    NewCharacterValues values =
        new NewCharacterValues("Sjaak", "idk", race, characterClass, weapon, tool, null);
    Result<PlayerCharacter> newCharacter = playerCharacterService.save(values);
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
    if (!raceService.getAll().isEmpty()) return;
    raceService.save("Dwarf", new Stats(3, 1, 4, 2, 2, 1));
    raceService.save("Elf", new Stats(2, 1, 4, 3, 3, 2));
    raceService.save("Orc", new Stats(3, 1, 3, 2, 2, 2));
    raceService.save("Human", new Stats(3, 1, 3, 3, 2, 2));
    raceService.save("Halfling", new Stats(2, 1, 4, 3, 2, 2));
    raceService.save("Gnome", new Stats(2, 1, 4, 3, 2, 2));
  }

  private void seedWeapons() {
    if (!weaponService.getAll().isEmpty()) return;
    weaponService.save("sword", "weapon", WeaponType.BLUNT, 45.);
    weaponService.save("axe", "weapon", WeaponType.PIERCING, 60.);
    weaponService.save("dagger", "weapon", WeaponType.SLASHING, 20.);
    weaponService.save("bow", "ranged", WeaponType.PIERCING, 35.);
    weaponService.save("spear", "weapon", WeaponType.BLUNT, 50.);
    weaponService.save("mace", "weapon", WeaponType.SLASHING, 55.);
  }
}
