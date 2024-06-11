package nl.itvitae.buildachar.character;

import java.util.List;
import nl.itvitae.buildachar.armor.Armor;
import nl.itvitae.buildachar.characterclass.CharacterClass;
import nl.itvitae.buildachar.race.Race;
import nl.itvitae.buildachar.tool.Tool;
import nl.itvitae.buildachar.weapon.Weapon;

public record NewCharacterValues(
    String name,
    String description,
    Race race,
    CharacterClass characterClass,
    Weapon weapon,
    Tool tool,
    List<Armor> armors) {}
