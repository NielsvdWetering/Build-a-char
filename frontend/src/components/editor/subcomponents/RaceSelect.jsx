export default function RaceSelect({ defaultValue, races, setSelectedRace }) {
  const description = "Select a race";

  return (
    <>
      <select
        className="select select-primary w-full"
        onChange={(event) => setSelectedRace(JSON.parse(event.target.value))}
        defaultValue={defaultValue ? JSON.stringify(defaultValue) : description}
      >
        <option disabled value={description}>
          {description}
        </option>
        {races.map((race) => (
          <option key={race.id} value={JSON.stringify(race)}>
            {race.name}
          </option>
        ))}
      </select>
    </>
  );
}
