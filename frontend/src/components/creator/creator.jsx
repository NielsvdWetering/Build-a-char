import React, { useEffect, useState } from "react";
import CreatorColumn from "./creatorColumn";
import RaceSelect from "./subcomponents/raceSelect";
import ClassSelect from "./subcomponents/classSelect";
import DescriptionInput from "./subcomponents/descriptionInput";
import axios from "axios";
import NameInput from "./subcomponents/nameInput";

export default function Creator() {
  const [name, setName] = useState("");

  const [selectedRace, setSelectedRace] = useState(null);
  const [races, setRaces] = useState([]);
  const [SelectedCharacterClass, setSelectedCharacterClass] = useState(null);
  const [characterClasses, setCharacterClasses] = useState([]);

  const [description, setDescription] = useState("");

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/v1/races")
      .catch(console.error)
      .then((response) => setRaces(response.data));

    axios
      .get("http://localhost:8080/api/v1/classes")
      .catch(console.error)
      .then((response) => setCharacterClasses(response.data));
  }, []);

  return (
    <>
      <div id="page" className="flex h-full justify-around">
        <CreatorColumn>
          <NameInput name={name} setName={setName} />
          <RaceSelect races={races} setSelectedRace={setSelectedRace} />
          <ClassSelect
            characterClasses={characterClasses}
            setSelectedCharacterClass={setSelectedCharacterClass}
          />
          <DescriptionInput
            description={description}
            setDescription={setDescription}
          />
        </CreatorColumn>
        <CreatorColumn>content in the 2nd column</CreatorColumn>
        <CreatorColumn>content in the 3th column</CreatorColumn>
      </div>
      <button onClick={() => console.log(name)}>Test</button>
    </>
  );
}
