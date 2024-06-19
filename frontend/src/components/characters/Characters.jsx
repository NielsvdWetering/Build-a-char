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
  const [url, setUrl] = useState("characters");
  const [raceParams, setRaceParams] = useState([]);
  const [classParams, setClassParams] = useState([]);
  const navigate = useNavigate();
  const { get } = useApi();

  useEffect(() => {
    fetchCharacters();
    fetchRaces();
    fetchClasses();
  }, []);

  useEffect(() => {
    fetchCharacters();
  }, [url]);

  useEffect(() => {
    const queryParams = [];

    if (raceParams.length > 0) {
      queryParams.push(`race=${raceParams}`);
    }

    if (classParams.length > 0) {
      queryParams.push(`class=${classParams}`);
    }

    if (queryParams.length > 0) {
      setUrl(`characters?${queryParams.join("&")}`);
    } else {
      setUrl("characters");
    }
  }, [raceParams, classParams]);

  const fetchCharacters = () => {
    get(url)
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

  const handleOnCheck = (target, isChecked) => {
    if (isChecked) {
      // add to
    } else {
      // delete from
    }
  };

  const handleCharacterFilter = (category, target, isChecked) => {
    if (isChecked) {
      if (category.toLowerCase() === "race") {
        setRaceParams((prev) => [...prev, target.toLowerCase()]);
      } else if (category.toLowerCase() === "class") {
        setClassParams((prev) => [...prev, target.toLowerCase()]);
      }
    } else {
      if (category.toLowerCase() === "race") {
        setRaceParams((prev) =>
          prev.filter((item) => item.toLowerCase() !== target.toLowerCase()),
        );
      } else if (category.toLowerCase() === "class") {
        setClassParams((prev) =>
          prev.filter((item) => item.toLowerCase() !== target.toLowerCase()),
        );
      }
    }
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
          <FilterByCategory
            category={"Class"}
            categoryItems={classes}
            handleCharacterFilter={handleCharacterFilter}
          />
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
