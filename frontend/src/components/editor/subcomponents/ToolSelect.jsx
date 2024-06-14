import DropdownSelect from "./DropdownSelect";

export default function ToolSelect({ defaultValue, tools, setSelectedTool }) {
  const description = "Select a tool";

  return (
    <DropdownSelect
      defaultValue={defaultValue}
      description={description}
      options={tools}
      handleChange={setSelectedTool}
    />
  );
}
