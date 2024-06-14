import DropdownSelect from "./DropdownSelect";

export default function RaceSelect({ defaultValue, races, setSelectedRace }) {
  const description = "Select a race";

  return (
    <DropdownSelect
      defaultValue={defaultValue}
      description={description}
      options={races}
      handleChange={setSelectedRace}
    />
  );
}
