import React from "react";
import CreatorColumn from "./creatorColumn";

export default function Creator() {
  return (
    <>
      <div id="page" className="flex h-full justify-around">
        <CreatorColumn>content in the 1st column</CreatorColumn>
        <CreatorColumn>content in the 2nd column</CreatorColumn>
        <CreatorColumn>content in the 3th column</CreatorColumn>
      </div>
    </>
  );
}
