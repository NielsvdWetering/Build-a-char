import { useNavigate, useParams } from "react-router-dom";
import PageColumn from "../PageColumn";
import { useState, useEffect } from "react";
import axios from "axios";
import { StatsView } from "./subcomponents/StatsView";
import { ArmorView } from "./subcomponents/ArmorView";
import { RingLoader } from "react-spinners";
import { CharacterImage } from "./subcomponents/CharacterImage";
import { CharacterDescription } from "./subcomponents/CharacterDescription";
import { CharacterInfo } from "./CharacterInfo";

export default function CharacterDisplay() {
  const [character, setCharacter] = useState();
  const { id } = useParams();
  const navigate = useNavigate();
  useEffect(() => {
    axios
      .get("http://localhost:8080/api/v1/characters/" + id)
      .then((response) => setCharacter(response.data))
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
      <div id="page" className="flex h-full justify-around">
        <PageColumn>
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
          <button className="btn btn-primary" onClick={() => navigate("edit")}>
            Edit
          </button>
        </PageColumn>
      </div>
    </>
  );
}
