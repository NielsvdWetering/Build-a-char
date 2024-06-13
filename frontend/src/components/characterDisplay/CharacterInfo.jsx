import React from "react";
import { Error } from "../generic/Error";

export const CharacterInfo = ({ name, race, characterClass }) => {
  return (
    <div className="aspect-video w-full p-4">
      {name ? (
        <h1 className="m-2 text-center text-6xl">{name}</h1>
      ) : (
        <Error msg={"Name could not be loaded"} />
      )}
      {race ? (
        <p className="border-b-2 border-primary p-4 text-4xl">{race}</p>
      ) : (
        <Error msg={"Race could not be loaded"} />
      )}
      {characterClass ? (
        <p className="border-b-2 border-primary p-4 text-4xl">
          {characterClass}
        </p>
      ) : (
        <Error msg={"Class could not be loaded"} />
      )}
    </div>
  );
};