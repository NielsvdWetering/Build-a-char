import InventoryItem from "./inventoryItem";

export default function Inventory({ weapons, tools }) {
  const weaponTooltip = (weapon) => {
    return (
      <div>
        <div className="flex justify-between">
          <span>Weapon Type</span> <span>{weapon.weaponType}</span>
        </div>
        <div className="flex justify-between">
          <span>Attack Power</span> <span>{weapon.attackPower}</span>
        </div>
      </div>
    );
  };

  const tooltip = "test2";
  return (
    <>
      <div className="my-5 flex w-full flex-col rounded-md bg-primary p-3">
        <h1 className="mb-2 border-b-2 border-primary-content text-xl font-semibold text-primary-content">
          Inventory:
        </h1>
        {weapons.map((item) => (
          <InventoryItem
            key={item.id}
            inventoryItem={item.name}
            tooltip={weaponTooltip(item)}
          />
        ))}
        {tools.map((item) => (
          <InventoryItem
            key={item.id}
            inventoryItem={item.name}
            tooltip={tooltip}
          />
        ))}
        {weaponTooltip(weapons[0])}
      </div>
    </>
  );
}
