export default function RaceSelect({ races, setSelectedRace }) {
  return (
    <>
      <select
        className="select select-secondary w-full"
        onChange={(event) => setSelectedRace(JSON.parse(event.target.value))}
      >
        {races.map((race) => (
          <option key={race.id} value={JSON.stringify(race)}>
            {race.name}
          </option>
        ))}
      </select>
    </>
  );
}
