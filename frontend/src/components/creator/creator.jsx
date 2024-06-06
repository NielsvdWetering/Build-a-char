import React, { useEffect, useState } from "react";
import CreatorColumn from "./creatorColumn";
import RaceSelect from "./subcomponents/raceSelect";
import ClassSelect from "./subcomponents/classSelect";
import axios from "axios";

export default function Creator() {
  const [selectedRace, setSelectedRace] = useState(null);
  const [races, setRaces] = useState([]);
  const [SelectedCharacterClass, setSelectedCharacterClass] = useState(null);
  const [characterClasses, setCharacterClasses] = useState([]);

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
          <ClassSelect
            characterClasses={characterClasses}
            setSelectedCharacterClass={setSelectedCharacterClass}
          />
          <RaceSelect races={races} setSelectedRace={setSelectedRace} />
        </CreatorColumn>
        <CreatorColumn>content in the 2nd column</CreatorColumn>
        <CreatorColumn>content in the 3th column</CreatorColumn>
      </div>
    </>
  );
}
