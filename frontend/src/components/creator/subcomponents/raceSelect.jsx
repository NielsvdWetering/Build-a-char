export default function RaceSelect({ races, setSelectedRace }) {
  return (
    <select
      className="select select-secondary w-full"
      onChange={(event) => setSelectedRace(JSON.parse(event.target.value))}
    >
      {races.map((r) => (
        <option key={r.id} value={JSON.stringify(r)}>
          {r.name}
        </option>
      ))}
    </select>
  );
}
