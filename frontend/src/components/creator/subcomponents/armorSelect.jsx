import DropdownSelect from "./dropdownSelect";

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
          defaultValue={"Select your head armor"}
          options={sortedArmorPieces.headArmor}
          handleChange={(newValue) => handleSelectChange(newValue, "head")}
        />
        <DropdownSelect
          defaultValue={"Select your torso armor"}
          options={sortedArmorPieces.torsoArmor}
          handleChange={(newValue) => handleSelectChange(newValue, "torso")}
        />
        <DropdownSelect
          defaultValue={"Select your leg armor"}
          options={sortedArmorPieces.legArmor}
          handleChange={(newValue) => handleSelectChange(newValue, "leg")}
        />
        <DropdownSelect
          defaultValue={"Select your hand armor"}
          options={sortedArmorPieces.handsArmor}
          handleChange={(newValue) => handleSelectChange(newValue, "hand")}
        />
        <DropdownSelect
          defaultValue={"Select your feet armor"}
          options={sortedArmorPieces.feetArmor}
          handleChange={(newValue) => handleSelectChange(newValue, "feet")}
        />
      </div>
    </>
  );
}
