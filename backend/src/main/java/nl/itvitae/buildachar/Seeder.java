package nl.itvitae.buildachar;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import nl.itvitae.buildachar.armor.Armor;
import nl.itvitae.buildachar.armor.ArmorClass;
import nl.itvitae.buildachar.armor.ArmorService;
import nl.itvitae.buildachar.armor.ArmorType;
import nl.itvitae.buildachar.character.NewCharacterValues;
import nl.itvitae.buildachar.character.PlayerCharacterService;
import nl.itvitae.buildachar.characterclass.CharacterClass;
import nl.itvitae.buildachar.characterclass.CharacterClassService;
import nl.itvitae.buildachar.race.Race;
import nl.itvitae.buildachar.race.RaceService;
import nl.itvitae.buildachar.race.Stats;
import nl.itvitae.buildachar.role.RoleName;
import nl.itvitae.buildachar.role.RoleRepository;
import nl.itvitae.buildachar.role.UserRole;
import nl.itvitae.buildachar.tool.Tool;
import nl.itvitae.buildachar.tool.ToolService;
import nl.itvitae.buildachar.user.User;
import nl.itvitae.buildachar.user.UserService;
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
  private final RoleRepository roleRepository;
  private final UserService userService;

  @Override
  public void run(String... args) throws Exception {
    seedClasses();
    seedArmors();
    seedTools();
    seedWeapons();
    seedRace();
    seedRoles();
    seedUsers();
    seedPlayerCharacters();
  }

  private void seedPlayerCharacters() {
    if (!playerCharacterService.getAll().isEmpty()) return;

    List<CharacterClass> allCharacterClasses = characterClassService.getAll();
    List<Race> allRaces = raceService.getAll();
    List<Weapon> allWeapons = weaponService.getAll();
    List<Tool> allTools = toolService.getAll();
    List<Armor> allArmors = armorService.getAll();
    List<User> allUsers = userService.getAll();

    if (allCharacterClasses.isEmpty()
        || allRaces.isEmpty()
        || allWeapons.isEmpty()
        || allTools.isEmpty()
        || allArmors.isEmpty()
        || allUsers.isEmpty()) {
      throw new IllegalStateException("Required entities for seeding are missing.");
    }

    Random random = new Random();

    for (int i = 0; i < 20; i++) {
      CharacterClass randomCharacterClass =
          allCharacterClasses.get(random.nextInt(allCharacterClasses.size()));
      Race randomRace = allRaces.get(random.nextInt(allRaces.size()));
      Weapon randomWeapon = allWeapons.get(random.nextInt(allWeapons.size()));
      Tool randomTool = allTools.get(random.nextInt(allTools.size()));
      List<Armor> randomArmors =
          random.ints(5, 0, allArmors.size()).mapToObj(allArmors::get).collect(Collectors.toList());
      User randomUser = allUsers.get(random.nextInt(allUsers.size()));

      NewCharacterValues values =
          new NewCharacterValues(
              "Character" + (i + 1),
              "Description" + (i + 1),
              randomRace,
              randomCharacterClass,
              randomWeapon,
              randomTool,
              randomArmors,
              null);

      playerCharacterService.save(values, randomUser);
    }
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

    armorService.save(
        "Steel Helmet",
        "A fierce looking dark helmet, should protect the wearer fairly good",
        4.2,
        ArmorType.HEAD,
        ArmorClass.HEAVY);
    armorService.save(
        "Steel Chestplate",
        "A polished silvery chestplate that protects the user while being quite fashionable",
        4.2,
        ArmorType.TORSO,
        ArmorClass.HEAVY);
    armorService.save(
        "Steel Leggins",
        "A pair of leggings made out of steel, nothing fashionable but is practical",
        4.2,
        ArmorType.LEGS,
        ArmorClass.HEAVY);
    armorService.save(
        "Steel Gauntlets",
        "A pair of slightly dented steel gauntlets, they clearly have seen some stuff",
        4.2,
        ArmorType.HANDS,
        ArmorClass.HEAVY);
    armorService.save(
        "Steel Boots",
        "A pair of muddied steel boots, I should make sure the squire learns how to clean them properly",
        4.2,
        ArmorType.FEET,
        ArmorClass.HEAVY);

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

  private void seedRoles() {
    List<String> persistedRoleNames =
        roleRepository.findAll().stream().map(UserRole::getName).toList();

    for (RoleName roleName : RoleName.values()) {
      if (!persistedRoleNames.contains(roleName.name())) {
        roleRepository.save(new UserRole(roleName));
      }
    }
  }

  private void seedUsers() {
    String username = "TestUser";
    String password = "Password_1";

    if (userService.getAll().stream().anyMatch(user -> user.getUsername().equals(username))) {
      return;
    }

    userService.save(username, password, RoleName.USER);
  }
}
