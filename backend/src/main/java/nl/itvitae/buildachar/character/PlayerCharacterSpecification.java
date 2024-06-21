package nl.itvitae.buildachar.character;

import java.util.Set;
import java.util.UUID;
import nl.itvitae.buildachar.characterclass.CharacterClass;
import nl.itvitae.buildachar.race.Race;
import org.springframework.data.jpa.domain.Specification;

public class PlayerCharacterSpecification {
  public static Specification<PlayerCharacter> hasUserId(UUID userId) {
    return ((root, query, criteriaBuilder) ->
        criteriaBuilder.equal(root.get("user").get("id"), userId));
  }

  public static Specification<PlayerCharacter> hasRaceIn(Set<Race> races) {
    return ((root, query, criteriaBuilder) -> root.get("race").in(races));
  }

  public static Specification<PlayerCharacter> hasClassIn(Set<CharacterClass> characterClasses) {
    return ((root, query, criteriaBuilder) -> root.get("characterClass").in(characterClasses));
  }

  public static Specification<PlayerCharacter> hasNameContainingIgnoreCase(String name) {
    return (root, query, criteriaBuilder) ->
        criteriaBuilder.like(
            criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
  }
}
