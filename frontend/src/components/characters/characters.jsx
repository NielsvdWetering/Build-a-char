import { Card } from "./Card";
import React, { useState, useEffect } from 'react';
import axios from 'axios';

export default function Characters() {
  const [characters, setCharacters] = useState([]);
  const [characterInfo, setCharacterInfo] = useState([]);
  const [characterEquipment, setCharacterEquipment] = useState([]);
  const [characterAttributes, setCharacterAttributes] = useState([]);

  useEffect(() => {
    fetchCharacters();
  }, []);

  useEffect(() => {
    if (characters.length > 0) {
      convertToCharacterFormat(characters);
    }
  }, [characters]);

  const fetchCharacters = () => {
    axios.get("http://localhost:8080/api/v1/characters")
      .then((response) => {
        console.log(response.data);
        setCharacters(response.data);
      })
      .catch((error) => {
        console.error("There was an error fetching the characters!", error);
      });
  }

  const convertToCharacterFormat = (characters) => {
    const info = characters.map(character => ({
      name: character.name,
      description: character.description
    }));

    // TODO goedzetten nadat ik die endpoint gefixed heb
    const equipment = characters.map(character =>({}));
    const attributes = characters.map(character => ({}));

    setCharacterInfo(info);
    setCharacterEquipment(equipment);
    setCharacterAttributes(attributes);
  }

  return (
    <>
    <div className="flex flex-row">
      <Card fields={[{"label":"name","content":"content"}]}/>
      <Card fields={[{"label":"name","content":"content"}]}/>
      <Card fields={[{"label":"name","content":"content"}]}/>
    </div>
    </>
  );
}