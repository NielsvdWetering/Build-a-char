package nl.itvitae.buildachar.characterclass;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterClassRepository extends JpaRepository<CharacterClass, UUID> {
  List<CharacterClass> findByNameIgnoreCaseIn(List<String> names);
}
