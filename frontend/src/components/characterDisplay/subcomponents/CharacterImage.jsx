import React from "react";
import Placeholder from "/img-placeholder.png";
import { useState, useEffect } from "react";

export const CharacterImage = ({ pictureId }) => {
  return (
    <div className="flex aspect-video w-full items-center justify-center rounded-md bg-accent text-accent-content shadow-custom-dark">
      <img
        src={
          pictureId != null
            ? `http://localhost:8080/api/v1/characters/image/${pictureId}`
            : Placeholder
        }
        alt="profile"
        className="aspect-video w-full rounded-md border border-solid border-gray-800"
      />
    </div>
  );
};
