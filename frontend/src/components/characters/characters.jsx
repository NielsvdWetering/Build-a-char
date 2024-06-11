import { Card } from "./Card";
import React, { useState, useEffect } from "react";
import axios from "axios";
import { StatsView } from "../characterDispay/StatsView";
import { useNavigate } from "react-router-dom";

export default function Characters() {
  const [characters, setCharacters] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    fetchCharacters();
    console.log(characters);
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
        <ul className="grid grid-cols-3 gap-2">
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
