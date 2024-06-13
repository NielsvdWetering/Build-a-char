export default function DropdownSelect({
  defaultValue,
  description,
  options,
  handleChange,
}) {
  const selectedOption = defaultValue
    ? options.find((option) => option.id === defaultValue.id)
    : undefined;
  return (
    <>
      <select
        className="select select-primary w-full"
        onChange={(event) => handleChange(JSON.parse(event.target.value))}
        defaultValue={
          selectedOption ? JSON.stringify(selectedOption) : description
        }
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
