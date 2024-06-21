import React from "react";
import { Error } from "../generic/Error";

export const CharacterInfo = ({ name, race, characterClass }) => {
  return (
    <div className="w-full p-4">
      {name ? (
        <h1 className="m-2 text-center text-5xl">{name}</h1>
      ) : (
        <Error msg={"Name could not be loaded"} />
      )}
      {race ? (
        <p className="border-b-2 border-primary p-4 text-xl">{race}</p>
      ) : (
        <Error msg={"Race could not be loaded"} />
      )}
      {characterClass ? (
        <p className="border-b-2 border-primary p-4 text-xl">
          {characterClass}
        </p>
      ) : (
        <Error msg={"Class could not be loaded"} />
      )}
    </div>
  );
};
