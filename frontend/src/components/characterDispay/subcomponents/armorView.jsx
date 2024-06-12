import React from "react";

export const ArmorView = ({ armorList }) => {
  const armorOrder = ["HEAD", "TORSO", "HANDS", "LEGS", "FEET"];
  return (
    <ul className="shadow-custom-dark aspect-video rounded-md bg-secondary p-5">
      {armorList
        .sort((a, b) => {
          return (
            armorOrder.indexOf(a.armorType) - armorOrder.indexOf(b.armorType)
          );
        })
        .map((armor) => (
          <li
            key={armor.id}
            className="grid-rows grid h-1/5 grid-cols-3 items-center gap-2 text-secondary-content"
          >
            <div className="grid grid-cols-2">
              <span className="capitalize">
                {armor.armorType.toLowerCase()}
              </span>
              <span className="capitalize">
                {armor.armorClass.toLowerCase()}
              </span>
            </div>
            <span>{armor.name}</span>
            <span>{armor.defence}</span>
          </li>
        ))}
    </ul>
  );
};
