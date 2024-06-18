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

  return (
    <>
      <div>
        <ul className="grid grid-cols-3 gap-2 p-3">
          {characters.map((character) => (
            <Card
              character={character}
              key={character.id}
              onClick={() => navigate("/characters/" + character.id)}
            />
          ))}
        </ul>
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
