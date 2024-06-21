import React from "react";

export const CharacterDescription = ({ description, className }) => {
  return (
    <div
      className={`body card w-full rounded-md bg-primary p-4 shadow-custom-dark ${className}`}
    >
      {description && <div className="card-title">Description</div>}
      <span className="text-primary-content">
        {description ? description : "No description"}
      </span>
    </div>
  );
};
