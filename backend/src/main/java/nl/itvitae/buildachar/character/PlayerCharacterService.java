package nl.itvitae.buildachar.character;

import static nl.itvitae.buildachar.armor.ArmorType.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import nl.itvitae.buildachar.armor.Armor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerCharacterService {
  @NonNull private PlayerCharacterRepository playerCharacterRepository;

  public List<PlayerCharacterDto> getAll() {
    List<PlayerCharacter> playerCharacters = playerCharacterRepository.findAll();
    return toCharacterDto(playerCharacters);
  }

  public List<PlayerCharacterDto> toCharacterDto(List<PlayerCharacter> playerCharacters) {
    return playerCharacters.stream().map(this::convertToDto).toList();
  }

  private PlayerCharacterDto convertToDto(PlayerCharacter playerCharacter) {
    String armorHead = null;
    String armorTorso = null;
    String armorLegs = null;
    String armorHands = null;
    String armorFeet = null;

    for (Armor armor : playerCharacter.getArmors()) {
      switch (armor.getArmorType()) {
        case HEAD -> armorHead = armor.getName();
        case TORSO -> armorTorso = armor.getName();
        case LEGS -> armorLegs = armor.getName();
        case HANDS -> armorHands = armor.getName();
        case FEET -> armorFeet = armor.getName();
      }
    }

    return new PlayerCharacterDto(
        playerCharacter.getId(),
        playerCharacter.getName() == null ? "unknown" : playerCharacter.getName(),
        playerCharacter.getDescription() == null ? "unknown" : playerCharacter.getDescription(),
        playerCharacter.getCharacterClass() == null
            ? "unknown"
            : playerCharacter.getCharacterClass().getName(),
        playerCharacter.getRace() == null ? "unknown" : playerCharacter.getRace().getName(),
        playerCharacter.getRace() != null ? playerCharacter.getRace().getRaceAttributes() : null,
        playerCharacter.getTool() == null ? "unknown" : playerCharacter.getTool().getName(),
        playerCharacter.getWeapon() == null ? "unknown" : playerCharacter.getWeapon().getName(),
        armorHead,
        armorTorso,
        armorLegs,
        armorHands,
        armorFeet);
  }

  public Optional<PlayerCharacter> getById(UUID id) {
    return playerCharacterRepository.findById(id);
  }

  public PlayerCharacter save(String name, String description) {
    return playerCharacterRepository.save(new PlayerCharacter(name, description));
  }
}
