package nl.itvitae.buildachar.character;

import static nl.itvitae.buildachar.armor.ArmorType.*;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import nl.itvitae.buildachar.patcher.Patcher;
import org.springframework.stereotype.Service;

@Service
public class PlayerCharacterService {
  private PlayerCharacterRepository playerCharacterRepository;
  private Patcher patcher;

  public PlayerCharacterService(
      PlayerCharacterRepository playerCharacterRepository, Patcher patcher) {
    this.playerCharacterRepository = playerCharacterRepository;
    this.patcher = patcher;
  }

  public List<PlayerCharacter> getAll() {
    return playerCharacterRepository.findAll();
  }

  public Optional<PlayerCharacter> getById(UUID id) {
    return playerCharacterRepository.findById(id);
  }

  public PlayerCharacter save(String name, String description) {
    return playerCharacterRepository.save(new PlayerCharacter(name, description));
  }

  public PlayerCharacter update(PlayerCharacter playerCharacter) {
    return playerCharacterRepository.save(playerCharacter);
  }

  public PlayerCharacter patch(UUID id, PlayerCharacterPatchDTO playerCharacterPatchDTO) {
    Optional<PlayerCharacter> optionalExistingPlayerCharacter =
        playerCharacterRepository.findById(id);
    if (optionalExistingPlayerCharacter.isEmpty())
      throw new EntityNotFoundException("Player Character with id " + id + " can not be found");
    PlayerCharacter existingPlayerCharacter = optionalExistingPlayerCharacter.get();

    try {
      Patcher.playerCharacterPatcher(existingPlayerCharacter, playerCharacterPatchDTO);
      // check if class is valid
      // save
      playerCharacterRepository.save(existingPlayerCharacter);
    } catch (Exception err) {
      System.out.println(err);
    }
    return null;
  }
}
