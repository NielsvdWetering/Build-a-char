import React, { useEffect, useState } from "react";
import { DisplayField } from "./DisplayField";
import { TextField } from "./TextField";
import PageColumn from "../../pageColumn";
import { useNavigate } from "react-router-dom";

export const Card = ({ character, onClick }) => {
  return (
    <div
      onClick={onClick}
      className="shadow-custom-dark cursor-pointer rounded bg-neutral p-2"
    >
      {character && (
        <>
          <DisplayField label={"name"} content={character.name} />
          <DisplayField label={"race"} content={character.race} />
          <DisplayField label={"class"} content={character.characterClass} />
        </>
      )}
    </div>
  );
};
