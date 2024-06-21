import { useNavigate, useParams } from "react-router-dom";
import PageColumn from "../PageColumn";
import { useState, useEffect } from "react";
import { StatsView } from "./subcomponents/StatsView";
import { ArmorView } from "./subcomponents/ArmorView";
import { RingLoader } from "react-spinners";
import { CharacterImage } from "./subcomponents/CharacterImage";
import { CharacterDescription } from "./subcomponents/CharacterDescription";
import Inventory from "./subcomponents/Inventory";
import { CharacterInfo } from "./CharacterInfo";
import { useApi } from "../../hooks";

export default function CharacterDisplay() {
  const [character, setCharacter] = useState();
  const { id } = useParams();
  const navigate = useNavigate();
  const { get } = useApi();

  useEffect(() => {
    get("characters/" + id)
      .then(setCharacter)
      .catch(console.error);
  }, []);

  if (!character) {
    return (
      <div className="flex flex-col items-center">
        <RingLoader color="#403D3A" cssOverride={{}} size={200} />
        <span>Loading</span>
      </div>
    );
  }

  return (
    <>
        <PageColumn>
      <div className="flex h-full justify-around">
          <CharacterImage />
          <CharacterInfo
            name={character.name}
            race={character.race.name}
            characterClass={character.characterClass.name}
          />
          <StatsView stats={character.stats} />
        </PageColumn>
        <PageColumn>
          <CharacterDescription description={character.description} />
        </PageColumn>
        <PageColumn>
          <ArmorView armorList={character.armorList} />
          <Inventory weapons={character.weapons} tools={character.tools} />
          {character.isOwner && (
            <button
              className="btn btn-primary mt-2 shadow-custom-dark"
              onClick={() => navigate("edit")}
            >
              Edit
            </button>
          )}
        </PageColumn>
      </div>
    </>
  );
}
