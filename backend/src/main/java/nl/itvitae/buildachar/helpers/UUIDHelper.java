package nl.itvitae.buildachar.helpers;

import java.util.Optional;
import java.util.UUID;

public class UUIDHelper {
  public static boolean isValidUUID(String value) {
    try {
      UUID uuid = UUID.fromString(value);

      return true;
    } catch (IllegalArgumentException ex) {
      return false;
    }
  }

  public static Optional<UUID> tryParseUUID(String value) {
    try {
      UUID uuid = UUID.fromString(value);

      return Optional.of(uuid);
    } catch (IllegalArgumentException ex) {
      return Optional.empty();
    }
  }
}
