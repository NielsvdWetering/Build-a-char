package nl.itvitae.buildachar.race;

public record BasicRaceDTO(String id, String name) {
  public static BasicRaceDTO from(Race race) {
    return new BasicRaceDTO(race.getId().toString(), race.getName());
  }
}
