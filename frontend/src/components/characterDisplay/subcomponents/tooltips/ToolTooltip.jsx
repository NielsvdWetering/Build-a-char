import TextTooltip from "./TextTooltip";

export default function ToolTooltip({ description }) {
  return (
    <div>
      <div className="w-40">
        <TextTooltip text={description} />
      </div>
    </div>
  );
}
