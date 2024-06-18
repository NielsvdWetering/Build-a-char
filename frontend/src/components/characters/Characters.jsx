import { Card } from "./subcomponents/Card";
import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useApi, useAuthentication } from "../../hooks";
import { ClipLoader } from "react-spinners";

export default function Characters({ ownedOnly }) {
  const { isLoggedIn } = useAuthentication();
  const [loggedIn, setLoggedIn] = useState(null);
  const navigate = useNavigate();
  const [characters, setCharacters] = useState([]);
  const { get } = useApi();
  useEffect(() => {
    if (ownedOnly && loggedIn === false) {
      navigate("/");
    }

    fetchCharacters();
  }, [loggedIn]);

  if (ownedOnly) {
    isLoggedIn().then((response) => {
      if (loggedIn === response) {
        return;
      }

      setLoggedIn(response);
    });

    if (loggedIn === null) {
      return (
        <div className="flex h-full w-full items-center justify-center">
          <ClipLoader size="500px" />
        </div>
      );
    }
  } else if (loggedIn !== null) {
    setLoggedIn(null);
  }

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
          <Sort handleOnCheck={handleOnCheck} />
          <Filter
            category={"Race"}
            categoryItems={races}
            handleCharacterFilter={handleCharacterFilter}
          />
          <Filter category={"Class"} categoryItems={classes} />
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

  function fetchCharacters() {
    if (ownedOnly && !loggedIn) {
      return;
    }

    get("characters", { ownedOnly })
      .then(setCharacters)
      .catch((error) => {
        console.error("There was an error fetching the characters!", error);
      });
  }
}
