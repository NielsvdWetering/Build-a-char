import React from "react";
import { Error } from "../../generic/Error";

export const CharacterImage = ({ image }) => {
  // This is commented out temporarily so you can still see the layout while we work on it.
  // Should be uncommented after implementen either an image or a placeholder image
  //   if (!image) return <Error msg={"Could not retrieve image"} />;
  return (
    <div className="aspect-video w-full rounded-md bg-accent text-accent-content shadow-custom-dark">
      CharacterImage
    </div>
  );
};
