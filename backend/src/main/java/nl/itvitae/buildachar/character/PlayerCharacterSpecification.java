package nl.itvitae.buildachar.character;

import java.util.Set;
import java.util.UUID;
import nl.itvitae.buildachar.characterclass.CharacterClass;
import nl.itvitae.buildachar.race.Race;
import org.springframework.data.jpa.domain.Specification;

public class PlayerCharacterSpecification {
  public static Specification<PlayerCharacter> hasUserId(UUID userId) {
    return ((root, query, criteriaBuilder) ->
        criteriaBuilder.equal(
            root.get("user").get("id"),
            userId)); // root(PlayerCharacter) waarbij user.id gelijk is aan userId
  }

  public static Specification<PlayerCharacter> hasRace(Race race) {
    return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("race"), race));
  }

  public static Specification<PlayerCharacter> hasClass(CharacterClass characterClass) {
    return ((root, query, criteriaBuilder) ->
        criteriaBuilder.equal(root.get("characterClass"), characterClass));
  }

  public static Specification<PlayerCharacter> hasRaceIn(Set<Race> races) {
    return ((root, query, criteriaBuilder) ->
        root.get("race").in(races)); // root(PlayerCharacter) waarbij race in opgegeven set van
    // races voorkomt
  }

  public static Specification<PlayerCharacter> hasClassIn(Set<CharacterClass> characterClasses) {
    return ((root, query, criteriaBuilder) -> root.get("characterClass").in(characterClasses));
  }

  public static Specification<PlayerCharacter> hasNameContainingIgnoreCase(String name) {
    return (root, query, criteriaBuilder) ->
        criteriaBuilder.like(
            criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
  }

  public static Specification<PlayerCharacter> hasRaceAndClass(
      Set<Race> races, Set<CharacterClass> classes) {
    return ((root, query, criteriaBuilder) ->
        criteriaBuilder.and(
            root.get("race").in(races),
            root.get("characterClass")
                .in(classes))); // root(PlayerCharacter) waarbij race in opgegeven set
    // van races voorkomt en characterClass in opgegeven set van classes
    // voorkomt
  }
}
