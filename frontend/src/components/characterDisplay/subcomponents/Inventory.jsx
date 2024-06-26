import CharacterItem from "./CharacterItem";
import { renderToStaticMarkup } from "react-dom/server";
import WeaponTooltip from "./tooltips/WeaponTooltip";
import ToolTooltip from "./tooltips/ToolTooltip";

export default function Inventory({ weapons, tools }) {
  return (
    <>
      <div className="flex w-full flex-col rounded-md bg-primary p-3">
        <h1 className="mb-2 border-b-2 border-primary-content text-xl font-semibold text-primary-content">
          Inventory:
        </h1>
        {weapons.map((item) => (
          <CharacterItem
            key={item.id}
            item={item.name}
            tooltip={renderToStaticMarkup(
              <WeaponTooltip
                type={item.weaponType}
                attackPower={item.attackPower}
              />,
            )}
          />
        ))}
        {tools.map((item) => (
          <CharacterItem
            key={item.id}
            item={item.name}
            tooltip={renderToStaticMarkup(
              <ToolTooltip description={item.description} />,
            )}
          />
        ))}
      </div>
    </>
  );
}
