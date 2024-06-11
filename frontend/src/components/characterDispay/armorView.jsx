import React from "react";

export const ArmorView = ({ armorList }) => {
  console.log("armorlist", armorList);
  return (
    <ul>
      {armorList.map((armor) => (
        <li key={armor.id}>
          <span>{armor.type}</span>
        </li>
      ))}
    </ul>
  );
};
