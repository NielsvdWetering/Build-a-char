import React from "react";
import { Error } from "../generic/Error";
import { AiFillEdit } from "react-icons/ai";

export const CharacterInfo = ({ name, race, characterClass, canEdit }) => {
  return (
    <div className="w-full p-4">
      <div className="flex gap-3">
        {canEdit && (
          <button className="btn btn-circle btn-secondary">
            <AiFillEdit size="1.5em" />
          </button>
        )}
      </div>
      {name ? (
        <h1 className="m-2 text-center text-4xl">{name}</h1>
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
