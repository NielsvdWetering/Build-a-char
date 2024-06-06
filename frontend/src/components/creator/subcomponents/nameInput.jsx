export default function NameInput({ name, setName }) {
  return (
    <input
      className="input input-secondary"
      value={name}
      onChange={(event) => setName(event.target.value)}
    />
  );
}
