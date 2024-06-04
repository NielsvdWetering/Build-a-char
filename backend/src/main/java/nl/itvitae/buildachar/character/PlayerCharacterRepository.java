package nl.itvitae.buildachar.character;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlayerCharacterRepository extends JpaRepository<PlayerCharacter, UUID> {}
