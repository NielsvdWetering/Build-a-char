import React from "react";
import { Error } from "../../generic/Error";
import CharacterItem from "./CharacterItem";
import ArmorTooltip from "./tooltips/ArmorTooltip";
import { renderToStaticMarkup } from "react-dom/server";

export const ArmorView = ({ armorList }) => {
  const armorOrder = ["HEAD", "TORSO", "HANDS", "LEGS", "FEET"];
  const sortedArmorList = [...armorList].sort((a, b) => {
    return armorOrder.indexOf(a.type) - armorOrder.indexOf(b.type);
  });
  if (!armorList) return <Error msg={"Could not retrieve armor pieces"} />;
  return (
    <>
      <div className="flex w-full flex-col rounded-md bg-primary p-3">
        <h1 className="mb-2 border-b-2 border-primary-content text-xl font-semibold text-primary-content">
          Armor:
        </h1>
        {sortedArmorList.map((armor) => (
          <CharacterItem
            key={armor.id}
            item={armor.name}
            tooltip={renderToStaticMarkup(<ArmorTooltip armor={armor} />)}
          />
        ))}
      </div>
    </>
  );
};
