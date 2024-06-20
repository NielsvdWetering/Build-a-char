package nl.itvitae.buildachar.characterclass;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterClassService {
  private final CharacterClassRepository characterClassRepository;

  public List<CharacterClass> getAll() {
    return characterClassRepository.findAll();
  }

  public Optional<CharacterClass> getById(UUID id) {
    return characterClassRepository.findById(id);
  }

  public CharacterClass save(String name) {
    return characterClassRepository.save(new CharacterClass(name));
  }

  public CharacterClass update(CharacterClass characterClass) {
    if (characterClass == null) {
      throw new IllegalArgumentException("CharacterClassService.update: characterClass is null");
    }

    return characterClassRepository.save(characterClass);
  }

  public Set<CharacterClass> getByName(Set<String> names) {
    return characterClassRepository.findByNameIgnoreCaseIn(names);
  }
}
