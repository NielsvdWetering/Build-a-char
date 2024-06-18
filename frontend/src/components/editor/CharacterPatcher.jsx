import { useNavigate, useParams } from "react-router-dom";
import CharacterEditor from "./CharacterEditor";
import { useEffect, useState } from "react";
import { RingLoader } from "react-spinners";
import { useApi } from "../../hooks";
import img from "/badbear.png";

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

  if (!character) return <RingLoader />;

  if (!character.isOwner)
    return (
      <div className="flex flex-col items-center p-6">
        <img src={img} className="w-40 rounded" />
        <p className="">You are not supposed to be here, you naughty Bear!</p>
      </div>
    );
  return (
    <CharacterEditor
      onSubmit={patchCharacter}
      submitLabel="Save"
      initialValues={character}
    />
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
