import InventoryItem from "./inventoryItem";
import { renderToStaticMarkup } from "react-dom/server";
import WeaponTooltip from "./weaponTooltip";
import ToolTooltip from "./toolTooltip";

export default function Inventory({ weapons, tools }) {
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
            tooltip={renderToStaticMarkup(
              <WeaponTooltip
                type={item.weaponType}
                attackPower={item.attackPower}
              />,
            )}
          />
        ))}
        {tools.map((item) => (
          <InventoryItem
            key={item.id}
            inventoryItem={item.name}
            tooltip={renderToStaticMarkup(
              <ToolTooltip description={item.description} />,
            )}
          />
        ))}
      </div>
    </>
  );
}
