package nl.itvitae.buildachar.race;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceRepository extends JpaRepository<Race, UUID> {
  Set<Race> findByNameIgnoreCaseIn(Set<String> names);

  Optional<Race> findByNameIgnoreCase(String name);
}
