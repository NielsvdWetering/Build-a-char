package nl.itvitae.buildachar.race;

import java.util.UUID;

public record BasicRaceDTO(UUID id, String name) {
  public static BasicRaceDTO from(Race race) {
    return new BasicRaceDTO(race.getId(), race.getName());
  }
}
