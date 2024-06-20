package nl.itvitae.buildachar.character;

import jakarta.transaction.Transactional;
import java.util.Set;
import java.util.UUID;
import nl.itvitae.buildachar.characterclass.CharacterClass;
import nl.itvitae.buildachar.race.Race;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Transactional
public interface PlayerCharacterRepository extends JpaRepository<PlayerCharacter, UUID> {
  Set<PlayerCharacter> findByUser_id(UUID id);

  Set<PlayerCharacter> findByRace(Race race);

  Set<PlayerCharacter> findByCharacterClass(CharacterClass characterClass);

  @Query(
      "SELECT pc FROM PlayerCharacter pc WHERE pc.race IN :races AND pc.characterClass IN :classes")
  Set<PlayerCharacter> findByRaceAndClass(
      @Param("races") Set<Race> races, @Param("classes") Set<CharacterClass> classes);

  Set<PlayerCharacter> findByNameContainingIgnoreCase(String param);
}
