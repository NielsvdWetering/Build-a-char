import React, { useEffect, useState } from "react";
import PageColumn from "../pageColumn";
import RaceSelect from "./subcomponents/raceSelect";
import ClassSelect from "./subcomponents/classSelect";
import DescriptionInput from "./subcomponents/descriptionInput";
import axios from "axios";
import NameInput from "./subcomponents/nameInput";
import ArmorSelect from "./subcomponents/armorSelect";
import WeaponSelect from "./subcomponents/weaponSelect";
import ToolSelect from "./subcomponents/toolSelect";

export default function Creator() {
  const [name, setName] = useState("");

  const [selectedRace, setSelectedRace] = useState(null);
  const [races, setRaces] = useState();
  const [SelectedCharacterClass, setSelectedCharacterClass] = useState(null);
  const [characterClasses, setCharacterClasses] = useState();
  const [sortedArmorPieces, setSortedArmorPieces] = useState();
  const [selectedArmorPieces, setSelectedArmorPieces] = useState({
    head: null,
    torso: null,
    leg: null,
    hand: null,
    feet: null,
  });

  const [description, setDescription] = useState("");

  const [weapons, setWeapons] = useState([]);
  const [selectedWeapon, setSelectedWeapon] = useState(null);

  const [tools, setTools] = useState([]);
  const [selectedTool, setSelectedTool] = useState(null);

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/v1/races")
      .then((response) => setRaces(response.data))
      .catch(console.error);

    axios
      .get("http://localhost:8080/api/v1/classes")
      .then((response) => setCharacterClasses(response.data))
      .catch(console.error);

    axios
      .get("http://localhost:8080/api/v1/armors/sortedByType")
      .then((response) => setSortedArmorPieces(response.data))
      .catch(console.error);

    axios
      .get("http://localhost:8080/api/v1/weapons")
      .then((response) => setWeapons(response.data))
      .catch(console.error);

    axios
      .get("http://localhost:8080/api/v1/tools")
      .then((response) => setTools(response.data))
      .catch(console.error);
  }, []);

  if (!races || !characterClasses || !sortedArmorPieces) {
    return <div>loading</div>;
  }

  return (
    <>
      <div id="page" className="flex h-full justify-around">
        <PageColumn>
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
        </PageColumn>
        <PageColumn>
          <ArmorSelect
            sortedArmorPieces={sortedArmorPieces}
            setSelectedArmorPieces={setSelectedArmorPieces}
            selectedArmorPieces={selectedArmorPieces}
          />
          <WeaponSelect
            weapons={weapons}
            setSelectedWeapon={setSelectedWeapon}
          />
          <ToolSelect tools={tools} setSelectedTool={setSelectedTool} />
        </PageColumn>
        <PageColumn>
          <button
            disabled={!name || name.length === 0}
            className="btn btn-primary"
            onClick={sumbitNewCharacter}
          >
            Create Character
          </button>
        </PageColumn>
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
      armorIds: Object.values(selectedArmorPieces)
        .filter((armorPiece) => armorPiece && armorPiece.id)
        .map((armorPiece) => armorPiece.id),
    };

    axios
      .post("http://localhost:8080/api/v1/characters", characterData)
      .catch(console.error)
      .then(console.log);
  }
}
