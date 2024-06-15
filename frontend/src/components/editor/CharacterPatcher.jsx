import { useNavigate, useParams } from "react-router-dom";
import CharacterEditor from "./CharacterEditor";
import { useEffect, useState } from "react";
import { RingLoader } from "react-spinners";
import { useApi } from "../../hooks";

export default function CharacterPatcher() {
  const { id } = useParams();
  const [character, setCharacter] = useState(null);
  const navigate = useNavigate();
  const { get, patch } = useApi();

  useEffect(() => {
    get(`characters/${id}`)
      .then(setCharacter)
      .catch(() => navigate("/"));
  }, []);

  return character ? (
    <CharacterEditor
      onSubmit={patchCharacter}
      submitLabel="Save"
      initialValues={character}
    />
  ) : (
    <RingLoader />
  );

  function patchCharacter(characterData) {
    patch(`characters/${id}`, {
      ...characterData,
      characterClassId: characterData.classId,
    })
      .then((response) => navigate(`/characters/${id}`))
      .catch(console.error);
  }
}
