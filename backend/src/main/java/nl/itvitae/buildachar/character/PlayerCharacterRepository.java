package nl.itvitae.buildachar.character;

import jakarta.transaction.Transactional;
import java.util.Set;
import java.util.UUID;
import nl.itvitae.buildachar.race.Race;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

// TODO add pageable!

@Transactional
public interface PlayerCharacterRepository
    extends JpaRepository<PlayerCharacter, UUID>, JpaSpecificationExecutor<PlayerCharacter> {

  Set<PlayerCharacter> findByRace(Race race);

  Set<PlayerCharacter> findByNameContainingIgnoreCase(String param);
  
 
}
