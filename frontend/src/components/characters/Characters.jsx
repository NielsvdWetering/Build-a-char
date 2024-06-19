import { Card } from "./subcomponents/Card";
import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useApi } from "../../hooks";
import { Filter } from "./subcomponents/Filter";
import { FilterByCategory } from "./subcomponents/FilterByCategory";
import InputField from "../generic/InputField";

export default function Characters() {
  const [characters, setCharacters] = useState([]);
  const [races, setRaces] = useState([]);
  const [classes, setClasses] = useState([]);
  const navigate = useNavigate();
  const { get } = useApi();

  useEffect(() => {
    fetchCharacters();
    fetchRaces();
    fetchClasses();
  }, []);

  const fetchCharacters = () => {
    get("characters")
      .then(setCharacters)
      .catch((error) => {
        console.error("There was an error fetching the characters!", error);
      });
  };

  const fetchRaces = () => {
    get("races")
      .then(setRaces)
      .catch((error) => {
        console.error("There was an error fetching the races!", error);
      });
  };

  const fetchClasses = () => {
    get("classes")
      .then(setClasses)
      .catch((error) => {
        console.error("There was an error fetching the classes!", error);
      });
  };

  const handleOnCheck = (target) => {
    console.log("We are going to sort by: ", target);
  };

  const handleCharacterFilter = (target) => {
    console.log("We are going to filter by: ", target);
  };

  return (
    <>
      <div className="grid h-full grid-flow-col">
        <div
          id="left-panel"
          className="col-span-1 grid-cols-subgrid border-r-4 p-2"
        >
          <Filter handleOnCheck={handleOnCheck} />
          <FilterByCategory
            category={"Race"}
            categoryItems={races}
            handleCharacterFilter={handleCharacterFilter}
          />
          <FilterByCategory category={"Class"} categoryItems={classes} />
        </div>

        <div className="col-span-3 grid-cols-subgrid">
          <div className="flex items-center justify-center p-2">
            <InputField
              placeholder={"search..."}
              className="w-2/3 text-center"
            />
          </div>
          <ul className="grid grid-cols-3 gap-12 p-3">
            {characters.map((character) => (
              <Card
                character={character}
                key={character.id}
                onClick={() => navigate("/characters/" + character.id)}
              />
            ))}
          </ul>
        </div>
      </div>
    </>
  );
}
