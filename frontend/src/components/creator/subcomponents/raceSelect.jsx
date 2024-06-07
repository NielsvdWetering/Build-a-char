export default function RaceSelect({ races, setSelectedRace }) {
  const defaultValue = "Select a race";

  return (
    <>
      <select
        className="select select-secondary w-full"
        onChange={(event) => setSelectedRace(JSON.parse(event.target.value))}
        value={defaultValue}
      >
        <option disabled value={defaultValue}>
          {defaultValue}
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
