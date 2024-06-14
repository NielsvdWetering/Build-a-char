export default function ToolTooltip({ description }) {
  console.log("description tooltip:");
  console.log(description);
  return (
    <div>
      <div className="w-40">
        <span className="">{description}</span>
      </div>
    </div>
  );
}
