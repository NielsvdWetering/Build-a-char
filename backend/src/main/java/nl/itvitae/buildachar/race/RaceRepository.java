package nl.itvitae.buildachar.race;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceRepository extends JpaRepository<Race, UUID> {
  List<Race> findByNameIgnoreCaseIn(List<String> names);
}
