export default function ToolSelect({ tools, setSelectedTool }) {
  const defaultValue = "Select a tool";

  return (
    <>
      <select
        className="select select-primary w-full"
        onChange={(event) => setSelectedTool(JSON.parse(event.target.value))}
        defaultValue={defaultValue}
      >
        <option disabled value={defaultValue}>
          {defaultValue}
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
