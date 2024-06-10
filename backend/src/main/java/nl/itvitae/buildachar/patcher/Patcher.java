package nl.itvitae.buildachar.patcher;

import java.lang.reflect.Field;
import nl.itvitae.buildachar.character.PlayerCharacter;
import nl.itvitae.buildachar.character.PlayerCharacterPatchDTO;
import org.springframework.stereotype.Component;

@Component
public class Patcher {
  public static void playerCharacterPatcher(
      PlayerCharacter existingPlayerCharacter, PlayerCharacterPatchDTO playerCharacterPatch)
      throws IllegalAccessException {
    Class<?> playerCharacterClass = PlayerCharacter.class;
    Field[] playerCharacterFields = playerCharacterClass.getDeclaredFields();
    for (Field field : playerCharacterFields) {
      field.setAccessible(true);

      Object value = field.get(playerCharacterPatch);
      if (value != null) field.set(existingPlayerCharacter, value);
      field.setAccessible(false);
    }
  }
}
