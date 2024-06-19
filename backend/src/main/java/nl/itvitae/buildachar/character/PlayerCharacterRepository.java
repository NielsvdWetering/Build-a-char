package nl.itvitae.buildachar.character;

import java.util.List;
import java.util.UUID;
import nl.itvitae.buildachar.characterclass.CharacterClass;
import nl.itvitae.buildachar.race.Race;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlayerCharacterRepository extends JpaRepository<PlayerCharacter, UUID> {
  List<PlayerCharacter> findByRace(Race race);

  List<PlayerCharacter> findByCharacterClass(CharacterClass characterClass);

  @Query(
      "SELECT pc FROM PlayerCharacter pc WHERE pc.race IN :races AND pc.characterClass IN :classes")
  List<PlayerCharacter> findByRaceAndClass(
      @Param("races") List<Race> races, @Param("classes") List<CharacterClass> classes);

  List<PlayerCharacter> findByNameContainingIgnoreCase(String param);
}
