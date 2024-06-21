import React from "react";
import Placeholder from "/img-placeholder.png";
import { useState, useEffect } from "react";

export const CharacterImage = ({ pictureId }) => {
  // const [pictureSrc, setPictureSrc] = useState("");
  // const [loading, setLoading] = useState();

  // useEffect(() => {
  //   setLoading(true);
  //   console.log("pictureId: " + pictureId);

  //   if (pictureId != null) {
  //     const link = `http://localhost:8080/api/v1/characters/image/${pictureId}`;
  //     console.log("this is the link " + link);
  //     setPictureSrc(link);
  //   } else {
  //     setPictureSrc("/img-placeholder.png");
  //   }

  //   console.log("the picture image source: " + pictureSrc);
  //   setLoading(false);
  // }, []);

  // if (loading) {
  //   return <div>loading</div>;
  // }

  return (
    <div className="flex aspect-video w-full items-center justify-center rounded-md bg-accent p-3 text-accent-content shadow-custom-dark">
      <div>
        <img
          src={
            pictureId != null
              ? `http://localhost:8080/api/v1/characters/image/${pictureId}`
              : "/img-placeholder.png"
          }
          alt="profile"
          className="max-h-60"
        />
      </div>
    </div>
  );
};
