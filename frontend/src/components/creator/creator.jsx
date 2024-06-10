import React, { useEffect, useState } from "react";
import CreatorColumn from "./creatorColumn";
import RaceSelect from "./subcomponents/raceSelect";
import ClassSelect from "./subcomponents/classSelect";
import DescriptionInput from "./subcomponents/descriptionInput";
import axios from "axios";
import NameInput from "./subcomponents/nameInput";
import ArmorSelect from "./subcomponents/armorSelect";

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

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/v1/races")
      .catch(console.error)
      .then((response) => setRaces(response.data));

    axios
      .get("http://localhost:8080/api/v1/classes")
      .then((response) => setCharacterClasses(response.data))
      .catch(console.error);

    axios
      .get("http://localhost:8080/api/v1/armors/sortedByType")
      .then((response) => setSortedArmorPieces(response.data))
      .catch(console.error);
  }, []);

  if (!races || !characterClasses || !sortedArmorPieces) {
    return <div>loading</div>;
  }

  return (
    <>
      <div id="page" className="bg-bear flex h-full justify-around bg-cover">
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
          <ArmorSelect
            sortedArmorPieces={sortedArmorPieces}
            setSelectedArmorPieces={setSelectedArmorPieces}
            selectedArmorPieces={selectedArmorPieces}
          />
        </CreatorColumn>
        <CreatorColumn>content in the 3th column</CreatorColumn>
      </div>
    </>
  );
}
