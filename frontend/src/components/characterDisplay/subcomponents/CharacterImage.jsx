import React from "react";
import Placeholder from "../../../../public/img-placeholder.png";

export const CharacterImage = ({ image }) => {
  return (
    <div className="flex aspect-video w-full items-center justify-center rounded-md bg-accent p-3 text-accent-content shadow-custom-dark">
      <div>
        <img
          src={image ? image : Placeholder}
          alt="profile"
          className="max-h-60"
        />
      </div>
    </div>
  );
};
