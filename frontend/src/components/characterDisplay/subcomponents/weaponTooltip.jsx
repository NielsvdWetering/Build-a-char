export default function WeaponTooltip({ type, attackPower }) {
  return (
    <div>
      <div className="flex justify-between">
        <span className="mr-4">Weapon Type:</span>{" "}
        <span className="mx-2">{type}</span>
      </div>
      <div className="flex justify-between">
        <span className="mr-4">Attack Power:</span>{" "}
        <span className="mx-2">{attackPower}</span>
      </div>
    </div>
  );
}
