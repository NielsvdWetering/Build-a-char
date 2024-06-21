import React, { useEffect, useState } from "react";
import PageColumn from "../PageColumn";
import DescriptionInput from "./subcomponents/DescriptionInput";
import NameInput from "./subcomponents/NameInput";
import ArmorSelect from "./subcomponents/ArmorSelect";
import { useApi, useAuthentication } from "../../hooks";
import DropdownSelect from "./subcomponents/DropdownSelect";
import InputGroup from "./subcomponents/InputGroup";
import AuthModal from "../auth/AuthModal";

const AUTH_DIALOG_ID = "authDialog";

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
    head: initialValues.armorList?.find((armor) => armor.type === "HEAD"),
    torso: initialValues.armorList?.find((armor) => armor.type === "TORSO"),
    leg: initialValues.armorList?.find((armor) => armor.type === "LEGS"),
    hand: initialValues.armorList?.find((armor) => armor.type === "HANDS"),
    feet: initialValues.armorList?.find((armor) => armor.type === "FEET"),
  });

  const [description, setDescription] = useState(
    initialValues.description ?? "",
  );

  const [weapons, setWeapons] = useState([]);
  const [selectedWeapon, setSelectedWeapon] = useState(
    initialValues.weapons?.[0],
  );

  const [tools, setTools] = useState([]);
  const [selectedTool, setSelectedTool] = useState(initialValues.tools?.[0]);

  const { get } = useApi();
  const { isLoggedIn } = useAuthentication();

  useEffect(() => {
    get("races").then(setRaces).catch(console.error);

    get("classes").then(setCharacterClasses).catch(console.error);

    get("armors/sortedByType").then(setSortedArmorPieces).catch(console.error);

    get("weapons").then(setWeapons).catch(console.error);

    get("tools").then(setTools).catch(console.error);
  }, []);

  if (!races || !characterClasses || !sortedArmorPieces) {
    return <div>loading</div>;
  }

  return (
    <>
      <div className="flex h-full max-h-full justify-around">
        <PageColumn>
          <InputGroup title="Character">
            <NameInput name={name} setName={setName} />
            <DropdownSelect
              defaultValue={selectedRace}
              description={"Select a race"}
              options={races}
              handleChange={setSelectedRace}
            />
            <DropdownSelect
              defaultValue={SelectedCharacterClass}
              description={"Select a class"}
              options={characterClasses}
              handleChange={setSelectedCharacterClass}
            />
            <DescriptionInput
              description={description}
              setDescription={setDescription}
            />
          </InputGroup>
        </PageColumn>
        <PageColumn>
          <ArmorSelect
            sortedArmorPieces={sortedArmorPieces}
            setSelectedArmorPieces={setSelectedArmorPieces}
            selectedArmorPieces={selectedArmorPieces}
          />
          <InputGroup title="Inventory">
            <DropdownSelect
              defaultValue={selectedWeapon}
              description={"Select a weapon"}
              options={weapons}
              handleChange={setSelectedWeapon}
            />
            <DropdownSelect
              defaultValue={selectedTool}
              description={"Select a tool"}
              options={tools}
              handleChange={setSelectedTool}
            />
          </InputGroup>
        </PageColumn>
        <PageColumn className="justify-between">
          <input
            type="file"
            accept="image/*"
            className="file-input file-input-primary"
          />
          <button
            disabled={!canSubmit()}
            className="btn btn-accent text-accent-content"
            onClick={handleSubmit}
          >
            {submitLabel ?? "Submit"}
          </button>
        </PageColumn>
        <AuthModal id={AUTH_DIALOG_ID} onCompleted={handleAuthCompleted} />
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

    isLoggedIn().then((loggedIn) => {
      if (!loggedIn) {
        showAuthModal();
        return;
      }

      submitCharacter();
    });
  }

  function showAuthModal() {
    document.getElementById(AUTH_DIALOG_ID).showModal();
  }

  function handleAuthCompleted() {
    submitCharacter();
  }

  function submitCharacter() {
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
