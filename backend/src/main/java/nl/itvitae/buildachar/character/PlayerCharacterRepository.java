package nl.itvitae.buildachar.character;

import java.util.Set;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerCharacterRepository extends JpaRepository<PlayerCharacter, UUID> {
  Set<PlayerCharacter> findByUser_id(UUID id);
}
