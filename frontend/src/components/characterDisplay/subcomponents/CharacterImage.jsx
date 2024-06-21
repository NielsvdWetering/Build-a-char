import React from "react";
import Placeholder from "/img-placeholder.png";
import { useState, useEffect } from "react";

export const CharacterImage = ({ pictureId }) => {
  return (
    <div className="flex aspect-video w-full items-center justify-center rounded-md bg-accent p-3 text-accent-content shadow-custom-dark">
      <div>
        <img
          src={
            pictureId != null
              ? `http://localhost:8080/api/v1/characters/image/${pictureId}`
              : Placeholder
          }
          alt="profile"
          className="max-h-60"
        />
      </div>
    </div>
  );
};
