import { useParams } from "react-router-dom";
import PageColumn from "../pageColumn";
import { useState, useEffect } from "react";
import axios from "axios";
import { StatsView } from "./StatsView";
import { ArmorView } from "./armorView";

export default function CharacterDisplay() {
  const [character, setCharacter] = useState();
  const { id } = useParams();
  useEffect(() => {
    axios
      .get("http://localhost:8080/api/v1/characters/" + id)
      .then((response) => setCharacter(response.data))
      .catch(console.error);
  }, []);

  if (!character) {
    return <div>loading</div>;
  }

  return (
    <>
      <div id="page" className="flex h-full justify-around">
        <PageColumn>
          <div className="aspect-video w-full rounded-md bg-accent text-accent-content">
            character picture
          </div>
          <div className="aspect-video rounded-md">
            <h1 className="text-3xl">{character.name}</h1>
          </div>
          <StatsView stats={character.stats} />
        </PageColumn>
        <PageColumn>
          <div className="h-full w-full rounded-md bg-secondary p-4">
            <span className="text-secondary-content">
              {character.description}
            </span>
          </div>
        </PageColumn>
        <PageColumn>
          <ArmorView armorList={character.armorList} />
        </PageColumn>
      </div>
    </>
  );
}
