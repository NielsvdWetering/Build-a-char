package nl.itvitae.buildachar.character;

import java.util.List;
import java.util.UUID;
import nl.itvitae.buildachar.characterclass.CharacterClass;
import nl.itvitae.buildachar.race.Race;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerCharacterRepository extends JpaRepository<PlayerCharacter, UUID> {
    List<PlayerCharacter> findByRace(Race race);
    List<PlayerCharacter> findByCharacterClass(CharacterClass characterClass);
}
