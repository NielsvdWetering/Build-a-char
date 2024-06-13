import { useNavigate, useParams } from "react-router-dom";
import CharacterEditor from "./CharacterEditor";
import { useEffect, useState } from "react";
import axios from "axios";
import { RingLoader } from "react-spinners";

export default function CharacterPatcher() {
  const { id } = useParams();
  const [character, setCharacter] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    axios
      .get(`http://localhost:8080/api/v1/characters/${id}`)
      .then((response) => setCharacter(response.data))
      .catch(() => navigate("/"));
  }, []);

  return character ? (
    <CharacterEditor
      onSubmit={patchCharacter}
      submitLabel="Save"
      initialValues={character}
    />
  ) : (
    <div>loading...</div>
  );

  function patchCharacter(characterData) {
    axios
      .patch(`http://localhost:8080/api/v1/characters/${id}`, {
        ...characterData,
        characterClassId: characterData.classId,
      })
      .then((response) => navigate(`/characters/${id}`))
      .catch(console.error);
  }
}
