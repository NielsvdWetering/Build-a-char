export default function ToolSelect({ defaultValue, tools, setSelectedTool }) {
  const description = "Select a tool";

  return (
    <>
      <select
        className="select select-primary w-full"
        onChange={(event) => setSelectedTool(JSON.parse(event.target.value))}
        defaultValue={defaultValue ? JSON.stringify(defaultValue) : description}
      >
        <option disabled value={description}>
          {description}
        </option>
        {tools.map((tool) => (
          <option key={tool.id} value={JSON.stringify(tool)}>
            {tool.name}
          </option>
        ))}
      </select>
    </>
  );
}
