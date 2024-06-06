package nl.itvitae.buildachar.race;

import java.util.List;
import java.util.Optional;
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

  public void save(
      String name,
      Double baseStrength,
      Double baseDexterity,
      Double baseConstitution,
      Double baseIntelligence,
      Double baseWisdom,
      Double baseCharisma) {
    raceRepository.save(
        new Race(
            name,
            baseStrength,
            baseDexterity,
            baseConstitution,
            baseIntelligence,
            baseWisdom,
            baseCharisma));
  }
}
