package nl.itvitae.buildachar.race;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class RaceService {
  private final RaceRepository raceRepository;

  public RaceService(RaceRepository raceRepository) {
    this.raceRepository = raceRepository;
  }

  public List<Race> getAll() {
    return raceRepository.findAll();
  }

  public Optional<Race> getById(UUID id) {
    return raceRepository.findById(id);
  }

  public void save(String name, Stats stats) {
    raceRepository.save(new Race(name, stats));
  }

  public void update(Race race) {
    if (race == null) {
      throw new IllegalArgumentException("RaceService.update: race is null");
    }

    raceRepository.save(race);
  }

  public Set<Race> getByName(Set<String> names) {
    return raceRepository.findByNameIgnoreCaseIn(names);
  }
}
