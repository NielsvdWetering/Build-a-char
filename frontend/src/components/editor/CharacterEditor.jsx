import React, { useEffect, useState } from "react";
import PageColumn from "../PageColumn";
import RaceSelect from "./subcomponents/RaceSelect";
import ClassSelect from "./subcomponents/ClassSelect";
import DescriptionInput from "./subcomponents/DescriptionInput";
import axios from "axios";
import NameInput from "./subcomponents/NameInput";
import ArmorSelect from "./subcomponents/ArmorSelect";
import WeaponSelect from "./subcomponents/WeaponSelect";
import ToolSelect from "./subcomponents/ToolSelect";

export default function CharacterEditor({
  onSubmit,
  submitLabel,
  initialValues,
}) {
  initialValues ??= {};

  const [name, setName] = useState(initialValues.name ?? "");

  const [races, setRaces] = useState();
  const [selectedRace, setSelectedRace] = useState(initialValues.race);

  const [characterClasses, setCharacterClasses] = useState();
  const [SelectedCharacterClass, setSelectedCharacterClass] = useState(
    initialValues.characterClass,
  );
  const [sortedArmorPieces, setSortedArmorPieces] = useState();
  const [selectedArmorPieces, setSelectedArmorPieces] = useState({
    head: initialValues.armors?.head,
    torso: initialValues.armors?.torso,
    leg: initialValues.armors?.leg,
    hand: initialValues.armors?.hand,
    feet: initialValues.armors?.feet,
  });

  const [description, setDescription] = useState("");

  const [weapons, setWeapons] = useState([]);
  const [selectedWeapon, setSelectedWeapon] = useState(initialValues.weapon);

  const [tools, setTools] = useState([]);
  const [selectedTool, setSelectedTool] = useState(initialValues.tool);

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
            disabled={!canSubmit()}
            className="btn btn-accent text-accent-content"
            onClick={handleSubmit}
          >
            {submitLabel ?? "Submit"}
          </button>
        </PageColumn>
      </div>
    </>
  );

  function canSubmit() {
    return name && name.length !== 0 && selectedRace && SelectedCharacterClass;
  }

  function handleSubmit() {
    if (!canSubmit()) {
      return;
    }

    onSubmit({
      name,
      description,
      raceId: selectedRace?.id,
      classId: SelectedCharacterClass?.id,
      weaponId: selectedWeapon?.id,
      toolId: selectedTool?.id,
      armorIds: Object.values(selectedArmorPieces)
        .filter((armorPiece) => armorPiece && armorPiece.id)
        .map((armorPiece) => armorPiece.id),
    });
  }
}
