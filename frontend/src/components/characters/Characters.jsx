import { Card } from "./subcomponents/Card";
import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { FilterByCategory } from "./subcomponents/FilterByCategory";
import { SearchBar } from "./subcomponents/SearchBar";
import { useApi, useAuthentication } from "../../hooks";
import { ClipLoader } from "react-spinners";

export default function Characters({ myCharacters, ownedOnly }) {
  const navigate = useNavigate();
  const { get } = useApi();

  const [characters, setCharacters] = useState([]);
  const [races, setRaces] = useState([]);
  const [classes, setClasses] = useState([]);
  const [url, setUrl] = useState("characters");
  const { isLoggedIn } = useAuthentication();
  const [loggedIn, setLoggedIn] = useState(null);
  const [raceParams, setRaceParams] = useState([]);
  const [classParams, setClassParams] = useState([]);

  const [detectClick, setDetectClick] = useState([]);

  const fetchCharacters = () => {
    get(url, {
      ownedOnly,
      race: raceParams.length > 0 ? raceParams.join(",") : undefined,
      class: classParams.length > 0 ? classParams.join(",") : undefined,
    })
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

  useEffect(() => {
    if (!ownedOnly || loggedIn === true) {
      fetchCharacters();
      return;
    }

    if (ownedOnly && loggedIn === false) {
      navigate("/");
    }
  }, [loggedIn, raceParams, classParams]);

  useEffect(() => {
    fetchRaces();
    fetchClasses();
  }, []);

  useEffect(() => {
    if (ownedOnly) {
      isLoggedIn().then((response) => {
        if (loggedIn !== response) {
          setLoggedIn(response);
        }
      });
    } else {
      setLoggedIn(null);
    }
  }, [ownedOnly]);

  if (ownedOnly && loggedIn === null) {
    return (
      <div className="flex h-full w-full items-center justify-center">
        <ClipLoader size="500px" />
      </div>
    );
  }

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
      <div
        className="grid h-full grid-flow-col"
        onClick={() => {
          setDetectClick(!detectClick);
        }}
      >
        <div
          id="left-panel"
          className="col-span-1 grid-cols-subgrid border-r-8 border-double border-secondary bg-secondary p-2"
        >
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
          <div className="rounded-lg bg-primary p-4 text-center text-xl font-bold text-primary-content">{`${myCharacters ? "My Characters" : "All Characters"}`}</div>
          <div className="mt-4 flex items-center justify-center p-2">
            <SearchBar detectClick={detectClick} />
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
