import React from "react";
import { Error } from "../../generic/error";

export const CharacterDescription = ({ description }) => {
  if (!description) return <Error msg={"Could not retrieve description"} />;
  return (
    <div className="shadow-custom-dark h-full w-full rounded-md bg-primary p-4">
      <span className="text-primary-content">{description}</span>
    </div>
  );
};
