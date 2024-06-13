import InventoryItem from "./inventoryItem";
import { renderToStaticMarkup } from "react-dom/server";

export default function Inventory({ weapons, tools }) {
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
            tooltip={renderToStaticMarkup(
              <div>
                <div className="flex justify-between">
                  <span className="mr-4">Weapon Type:</span>{" "}
                  <span className="mx-2">{item.weaponType}</span>
                </div>
                <div className="flex justify-between">
                  <span className="m2-4">Attack Power:</span>{" "}
                  <span className="mx-2">{item.attackPower}</span>
                </div>
              </div>,
            )}
          />
        ))}
        {tools.map((item) => (
          <InventoryItem
            key={item.id}
            inventoryItem={item.name}
            tooltip={tooltip}
          />
        ))}
      </div>
    </>
  );
}
