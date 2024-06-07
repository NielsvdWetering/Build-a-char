export default function RaceSelect({
  characterClasses,
  setSelectedCharacterClass,
}) {
  const defaultValue = "Select your class";
  return (
    <>
      <select
        className="select select-secondary w-full"
        onChange={(event) =>
          setSelectedCharacterClass(JSON.parse(event.target.value))
        }
        value={defaultValue}
      >
        <option disabled value={defaultValue}>
          Select your class
        </option>
        {characterClasses.map((characterClass) => (
          <option
            key={characterClass.id}
            value={JSON.stringify(characterClass)}
          >
            {characterClass.name}
          </option>
        ))}
      </select>
    </>
  );
}
