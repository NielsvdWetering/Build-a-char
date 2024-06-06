import React from "react";
import { useState } from "react";
import CreatorColumn from "./creatorColumn";
import ClassSelect from "./subcomponents/classSelect";

export default function Creator() {
  const [characterClass, setCharacterClass] = useState();

  return (
    <>
      <div id="page" className="flex h-full justify-around">
        <CreatorColumn>
          <ClassSelect />
        </CreatorColumn>
        <CreatorColumn>content in the 2nd column</CreatorColumn>
        <CreatorColumn>content in the 3th column</CreatorColumn>
      </div>
    </>
  );
}
