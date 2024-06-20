import React from "react";
import Placeholder from "/cutebear.jpeg";

export const CharacterImage = ({ image }) => {
  return (
    <div className="rounded-mdp-3 flex aspect-video w-full items-center justify-center bg-accent text-accent-content shadow-custom-dark">
      <div>
        <img
          src={image ? image : Placeholder}
          alt="profile"
          className="h-72 rounded-lg"
        />
      </div>
    </div>
  );
};
