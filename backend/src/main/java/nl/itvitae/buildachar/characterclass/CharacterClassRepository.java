package nl.itvitae.buildachar.characterclass;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterClassRepository extends JpaRepository<CharacterClass, UUID> {
  Set<CharacterClass> findByNameIgnoreCaseIn(Set<String> names);

  Optional<CharacterClass> findByNameIgnoreCase(String name);
}
