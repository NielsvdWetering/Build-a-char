package nl.itvitae.buildachar.character;

import jakarta.transaction.Transactional;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface PlayerCharacterRepository extends JpaRepository<PlayerCharacter, UUID> {
  Set<PlayerCharacter> findByUser_id(UUID id);
}
