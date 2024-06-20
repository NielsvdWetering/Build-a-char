package nl.itvitae.buildachar.race;

import java.util.Set;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceRepository extends JpaRepository<Race, UUID> {
  Set<Race> findByNameIgnoreCaseIn(Set<String> names);

  Race findByNameIgnoreCase(String name);
}
