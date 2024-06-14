import axios from "axios";
import CharacterEditor from "./CharacterEditor";
import { useNavigate } from "react-router-dom";

export default function CharacterCreator() {
  const navigate = useNavigate();

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
      .then((response) => {
        console.log(response), navigate(`/characters/${response.data.id}`);
      });
  }
}
