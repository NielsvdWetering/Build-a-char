import React from "react";
import { Error } from "../../generic/error";

export const CharacterDescription = ({ description }) => {
  return (
    <div className="h-full w-full rounded-md bg-primary p-4 shadow-custom-dark">
      <span className="text-primary-content">{description}</span>
    </div>
  );
};
