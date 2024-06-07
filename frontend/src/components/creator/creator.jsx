import React, { useEffect, useState } from "react";
import CreatorColumn from "./creatorColumn";
import RaceSelect from "./subcomponents/raceSelect";
import ClassSelect from "./subcomponents/classSelect";
import DescriptionInput from "./subcomponents/descriptionInput";
import axios from "axios";
import NameInput from "./subcomponents/nameInput";
import WeaponSelect from "./subcomponents/weaponSelect";
import ToolSelect from "./subcomponents/toolSelect";

export default function Creator() {
  const [name, setName] = useState("");

  const [selectedRace, setSelectedRace] = useState(null);
  const [races, setRaces] = useState([]);
  const [SelectedCharacterClass, setSelectedCharacterClass] = useState(null);
  const [characterClasses, setCharacterClasses] = useState([]);

  const [description, setDescription] = useState("");

  const [weapons, setWeapons] = useState([]);
  const [selectedWeapon, setSelectedWeapon] = useState(null);

  const [tools, setTools] = useState([]);
  const [selectedTool, setSelectedTool] = useState(null);

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/v1/races")
      .catch(console.error)
      .then((response) => setRaces(response.data));

    axios
      .get("http://localhost:8080/api/v1/classes")
      .catch(console.error)
      .then((response) => setCharacterClasses(response.data));

    axios
      .get("http://localhost:8080/api/v1/weapons")
      .catch(console.error)
      .then((response) => setWeapons(response.data));

    axios
      .get("http://localhost:8080/api/v1/tools")
      .catch(console.error)
      .then((response) => setTools(response.data));
  }, []);

  console.log(tools);

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
        <CreatorColumn>
          <WeaponSelect
            weapons={weapons}
            setSelectedWeapon={setSelectedWeapon}
          />
          <ToolSelect tools={tools} setSelectedTool={setSelectedTool} />
        </CreatorColumn>
        <CreatorColumn>
          <button
            disabled={!name || name.length === 0}
            className="btn btn-primary"
            onClick={sumbitNewCharacter}
          >
            Create Character
          </button>
        </CreatorColumn>
      </div>
    </>
  );

  function sumbitNewCharacter() {
    const characterData = {
      name,
      description,
      raceId: selectedRace?.id,
      classId: SelectedCharacterClass?.id,
      weaponId: selectedWeapon?.id,
      toolId: selectedTool?.id,
    };

    axios
      .post("http://localhost:8080/api/v1/characters", characterData)
      .catch(console.error)
      .then(console.log);
  }
}
