import axios from "axios";
import CharacterEditor from "./CharacterEditor";

export default function CharacterCreator() {
  return (
    <CharacterEditor
      onSubmit={createCharacter}
      submitLabel="Create Character"
    />
  );

  function createCharacter(characterData) {
    axios
      .post("http://localhost:8080/api/v1/characters", characterData)
      .catch(console.error)
      .then(console.log);
  }
}
