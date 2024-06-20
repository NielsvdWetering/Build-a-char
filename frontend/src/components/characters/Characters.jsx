import { Card } from "./subcomponents/Card";
import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import useApi from "../../hooks/useApi";
import { Filter } from "./subcomponents/Filter";
import { FilterByCategory } from "./subcomponents/FilterByCategory";
import { SearchBar } from "./subcomponents/SearchBar";

export default function Characters() {
  const navigate = useNavigate();
  const { get } = useApi();

  const [characters, setCharacters] = useState([]);
  const [races, setRaces] = useState([]);
  const [classes, setClasses] = useState([]);
  const [url, setUrl] = useState("characters");

  const [raceParams, setRaceParams] = useState([]);
  const [classParams, setClassParams] = useState([]);

  useEffect(() => {
    const queryParams = [];
    if (raceParams.length > 0) {
      queryParams.push(`race=${raceParams.join(",")}`);
    }
    if (classParams.length > 0) {
      queryParams.push(`class=${classParams.join(",")}`);
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

  useEffect(() => {
    fetchCharacters();
  }, [url]);

  useEffect(() => {
    fetchCharacters();
    fetchRaces();
    fetchClasses();
  }, []);

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

  const handleOnCheck = (target) => {
    console.log("Filter by: ", target);
  };

  return (
    <div className="grid h-full grid-flow-col">
      <div
        id="left-panel"
        className="col-span-1 grid-cols-subgrid border-r-8 border-double border-secondary bg-secondary p-2"
      >
        {/* <Filter handleOnCheck={handleOnCheck} />  */}
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

      <div id="right-panel" className="col-span-3 grid-cols-subgrid p-8">
        <div className="flex items-center justify-center p-2">
          <SearchBar />
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
  );
}
