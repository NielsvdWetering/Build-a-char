export default function WeaponTooltip({ armor }) {
  return (
    <div className="w-48">
      <div className="flex justify-between">
        <span className="mr-4">Armor Type:</span>{" "}
        <span className="mx-2">{armor.type}</span>
      </div>
      <div className="flex justify-between">
        <span className="mr-4">Armor class:</span>{" "}
        <span className="mx-2">{armor.armorClass}</span>
      </div>
      <div className="flex justify-between">
        <span className="mr-4">Defence:</span>{" "}
        <span className="mx-2">{armor.defence}</span>
      </div>
      <span className="my-2 flex flex-wrap">{armor.description}</span>
    </div>
  );
}
