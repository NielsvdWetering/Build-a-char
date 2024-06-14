import DropdownSelect from "./DropdownSelect";

export default function ClassSelect({
  defaultValue,
  characterClasses,
  setSelectedCharacterClass,
}) {
  const description = "Select your class";

  return (
    <DropdownSelect
      defaultValue={defaultValue}
      description={description}
      options={characterClasses}
      handleChange={setSelectedCharacterClass}
    />
  );
}
