import DropdownSelect from "./DropdownSelect";

export default function ArmorSelect({
  sortedArmorPieces,
  setSelectedArmorPieces,
  selectedArmorPieces,
}) {
  const handleSelectChange = (newValue, type) => {
    setSelectedArmorPieces({ ...selectedArmorPieces, [type]: newValue });
  };

  return (
    <>
      <div>
        <DropdownSelect
          description={"Select your head armor"}
          options={sortedArmorPieces.headArmor}
          handleChange={(newValue) => handleSelectChange(newValue, "head")}
        />
        <DropdownSelect
          description={"Select your torso armor"}
          options={sortedArmorPieces.torsoArmor}
          handleChange={(newValue) => handleSelectChange(newValue, "torso")}
        />
        <DropdownSelect
          description={"Select your leg armor"}
          options={sortedArmorPieces.legArmor}
          handleChange={(newValue) => handleSelectChange(newValue, "leg")}
        />
        <DropdownSelect
          description={"Select your hand armor"}
          options={sortedArmorPieces.handsArmor}
          handleChange={(newValue) => handleSelectChange(newValue, "hand")}
        />
        <DropdownSelect
          description={"Select your feet armor"}
          options={sortedArmorPieces.feetArmor}
          handleChange={(newValue) => handleSelectChange(newValue, "feet")}
        />
      </div>
    </>
  );
}
