import React, { useEffect, useState } from "react";
import { DisplayField } from "./DisplayField";
import { TextField } from "./TextField";

export const Card = ({ character }) => {
  return (
    <div className="border-bg-primary m-10 w-1/2 flex-row rounded-lg border-2 bg-emerald-800 p-8 text-sky-50">
      {character ? (
        <>
          <div className="font-semibold text-primary-content">
            <DisplayField label={"name"} content={character.name} />
            <DisplayField label={"race"} content={character.race} />
            <DisplayField label={"class"} content={character.characterClass} />
            <TextField label={"description"} content={character.description} />
          </div>
          <div className="border-b-2 font-semibold text-primary-content">
            <DisplayField label={"weapon"} content={character.weapon} />
            <DisplayField label={"head"} content={character.armorHead} />
            <DisplayField label={"torso"} content={character.armorTorso} />
            <DisplayField label={"hands"} content={character.armorHands} />
            <DisplayField label={"legs"} content={character.armorLegs} />
            <DisplayField label={"feet"} content={character.armorFeet} />
            <DisplayField label={"tool"} content={character.tools} />
          </div>

          <div className="font-semibold text-primary-content">
            <DisplayField
              label={"Strength"}
              content={character.stats.baseStrength}
            />
            <DisplayField
              label={"Dexterity"}
              content={character.stats.baseDexterity}
            />
            <DisplayField
              label={"Constitution"}
              content={character.stats.baseConstitution}
            />
            <DisplayField
              label={"Intelligence"}
              content={character.stats.baseIntelligence}
            />
            <DisplayField
              label={"Wisdom"}
              content={character.stats.baseWisdom}
            />
            <DisplayField
              label={"Charisma"}
              content={character.stats.baseCharisma}
            />
          </div>
        </>
      ) : null}
    </div>
  );
};
