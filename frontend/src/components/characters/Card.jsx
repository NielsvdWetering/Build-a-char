import React, { useEffect, useState } from "react";
import { DisplayField } from "./DisplayField";
import { TextField } from "./TextField";
import CreatorColumn from "../creator/creatorColumn";
import { StatsView } from "./StatsView";

export const Card = ({ character }) => {
  const tempStats = {
    strength: 4,
    dexterity: 5,
    constitution: 123,
    intelligence: 21123,
    wisdom: 123312,
    charisma: 1231,
  };
  //console.log("CHARACTER", character);
  return (
    // <div className="grid grid-flow-col border-2 border-emerald-900 p-10">
    //   {character && (
    //     <>
    //       <CreatorColumn>
    //         <DisplayField label={"name"} content={character.name} />
    //         <DisplayField label={"race"} content={character.race} />
    //         <DisplayField label={"class"} content={character.characterClass} />
    //         <TextField label={"description"} content={character.description} />
    //       </CreatorColumn>
    //       <CreatorColumn>
    //         <DisplayField label={"weapon"} content={character.weapon} />
    //         <DisplayField label={"head"} content={character.armorHead} />
    //         <DisplayField label={"torso"} content={character.armorTorso} />
    //         <DisplayField label={"hands"} content={character.armorHands} />
    //         <DisplayField label={"legs"} content={character.armorLegs} />
    //         <DisplayField label={"feet"} content={character.armorFeet} />
    //         <DisplayField label={"tool"} content={character.tool} />
    //       </CreatorColumn>
    //       {character.stats && (
    //         <CreatorColumn>
    //           <DisplayField
    //             label={"Strength"}
    //             content={character.stats.baseStrength}
    //           />
    //           <DisplayField
    //             label={"Dexterity"}
    //             content={character.stats.baseDexterity}
    //           />
    //           <DisplayField
    //             label={"Constitution"}
    //             content={character.stats.baseConstitution}
    //           />
    //           <DisplayField
    //             label={"Intelligence"}
    //             content={character.stats.baseIntelligence}
    //           />
    //           <DisplayField
    //             label={"Wisdom"}
    //             content={character.stats.baseWisdom}
    //           />
    //           <DisplayField
    //             label={"Charisma"}
    //             content={character.stats.baseCharisma}
    //           />
    //         </CreatorColumn>
    //       )}
    //     </>
    //   )}
    // </div>
    <></>
  );
};
