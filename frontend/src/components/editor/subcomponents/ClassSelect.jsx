export default function ClassSelect({
  defaultValue,
  characterClasses,
  setSelectedCharacterClass,
}) {
  const description = "Select your class";

  return (
    <>
      <select
        className="select select-primary w-full"
        onChange={(event) =>
          setSelectedCharacterClass(JSON.parse(event.target.value))
        }
        defaultValue={defaultValue ? JSON.stringify(defaultValue) : description}
      >
        <option disabled value={description}>
          {description}
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
