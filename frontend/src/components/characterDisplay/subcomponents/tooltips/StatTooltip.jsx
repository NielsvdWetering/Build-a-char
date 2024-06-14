export default function StatTooltip({ label, value }) {
  return (
    <div className="flex w-11/12 justify-between">
      <span>{label}</span>
      <span>{value}</span>
    </div>
  );
}
