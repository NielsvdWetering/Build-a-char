import CharacterEditor from "./CharacterEditor";
import { useNavigate } from "react-router-dom";
import { useApi } from "../../hooks";

export default function CharacterCreator() {
  const navigate = useNavigate();
  const { post } = useApi();

  return (
    <CharacterEditor
      onSubmit={createCharacter}
      submitLabel="Create Character"
    />
  );

  function createCharacter(characterData) {
    post("characters", characterData)
      .then((response) => navigate(`/characters/${response.id}`))
      .catch(console.error);
  }
}
