export default function StatTooltip({ label, value }) {
  return (
    <div className="flex justify-between">
      <span className="mr-4">{label}</span>{" "}
      <span className="mx-2">{value}</span>
    </div>
  );
}
