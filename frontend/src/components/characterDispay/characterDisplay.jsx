import { useParams } from "react-router-dom";
import PageColumn from "../pageColumn";
import { useState, useEffect } from "react";
import axios from "axios";
import { StatsView } from "./subcomponents/StatsView";
import { ArmorView } from "./subcomponents/armorView";
import { RingLoader } from "react-spinners";

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
          <div className="aspect-video w-full rounded-md bg-accent text-accent-content">
            character picture
          </div>
          <div className="aspect-video rounded-md">
            <h1 className="text-3xl">{character.name}</h1>
          </div>
          {character.armorList ? (
            <StatsView stats={character.stats} />
          ) : (
            <Error msg={"armor list does not exist"} />
          )}
        </PageColumn>
        <PageColumn>
          <div className="h-full w-full rounded-md bg-primary p-4">
            <span className="text-primary-content">
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
