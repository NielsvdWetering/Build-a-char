import { Card } from "./subcomponents/Card";
import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useApi } from "../../hooks";

export default function Characters({ ownedOnly }) {
  const [characters, setCharacters] = useState([]);
  const navigate = useNavigate();
  const { get } = useApi();

  useEffect(() => {
    fetchCharacters();
  }, []);

  const fetchCharacters = () => {
    get("characters")
      .then(setCharacters)
      .catch((error) => {
        console.error("There was an error fetching the characters!", error);
      });
  };

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
}
