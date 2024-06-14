import DropdownSelect from "./DropdownSelect";

export default function WeaponSelect({
  defaultValue,
  weapons,
  setSelectedWeapon,
}) {
  const description = "Select a weapon";

  return (
    <DropdownSelect
      defaultValue={defaultValue}
      description={description}
      options={weapons}
      handleChange={setSelectedWeapon}
    />
  );
}
