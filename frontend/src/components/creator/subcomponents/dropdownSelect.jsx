export default function DropdownSelect({
  defaultValue,
  options,
  handleChange,
}) {
  return (
    <>
      <select
        className="select select-secondary w-full"
        onChange={(event) => handleChange(JSON.parse(event.target.value))}
        defaultValue={defaultValue}
      >
        <option disabled value={defaultValue}>
          {defaultValue}
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
