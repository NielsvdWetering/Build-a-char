export default function DescriptionInput({ description, setDescription }) {
  return (
    <textarea
      className="textarea textarea-primary"
      value={description}
      onChange={(event) => setDescription(event.target.value)}
      placeholder="character description"
    />
  );
}
