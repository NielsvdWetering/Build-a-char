export default function DropdownSelect({ description, options, handleChange }) {
  return (
    <>
      <select
        className="select select-secondary w-full"
        onChange={(event) => handleChange(JSON.parse(event.target.value))}
        defaultValue={description}
      >
        <option disabled value={description}>
          {description}
        </option>
        {options.map((option) => (
          <option key={option.id} value={JSON.stringify(option)}>
            {option.name}
          </option>
        ))}
      </select>
    </>
  );
}
