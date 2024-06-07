import { Card } from "./Card";
import React, { useState, useEffect } from "react";
import axios from "axios";

export default function Characters() {
  const [characters, setCharacters] = useState([]);

  useEffect(() => {
    fetchCharacters();
  }, []);

  const fetchCharacters = () => {
    axios
      .get("http://localhost:8080/api/v1/characters")
      .then((response) => {
        setCharacters(response.data);
        console.log(characters);
      })
      .catch((error) => {
        console.error("There was an error fetching the characters!", error);
      });
  };

  return (
    <>
      <div>
        <ul className="flex flex-row">
          {characters.map((character) => (
            <Card character={character} key={character.id} />
          ))}
        </ul>
      </div>
    </>
  );
}
