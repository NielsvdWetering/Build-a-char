package nl.itvitae.buildachar.characterclass;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CharacterClassRepository extends JpaRepository<CharacterClass, UUID> {}
