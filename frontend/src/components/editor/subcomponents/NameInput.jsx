export default function NameInput({ name, setName }) {
  return (
    <input
      className="input input-lg input-primary"
      value={name}
      onChange={(event) => setName(event.target.value)}
      placeholder="character name"
    />
  );
}
