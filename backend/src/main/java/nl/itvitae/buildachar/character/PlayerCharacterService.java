package nl.itvitae.buildachar.character;

import static nl.itvitae.buildachar.armor.ArmorType.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerCharacterService {
  @NonNull private PlayerCharacterRepository playerCharacterRepository;

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
}
