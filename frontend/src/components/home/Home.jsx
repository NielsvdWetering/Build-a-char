import React from "react";
import CharacterCreator from "../editor/CharacterCreator";
import { useState, useEffect } from "react";
import { useApi } from "../../hooks";

export default function Home() {
  const [img, setImg] = useState();
  const [loading, setLoading] = useState();

  useEffect(() => {
    fetchImage();
  }, []);

  const fetchImage = async () => {
    setLoading(true);
    const res = await fetch(
      "http://localhost:8080/api/v1/characters/image/f3471312-1c68-4597-ba19-98bb92e22fb7",
    );
    const imageBlob = await res.blob();
    const imageObjectURL = URL.createObjectURL(imageBlob);
    setImg(imageObjectURL);
    console.log(img);
    setLoading(false);
  };

  if (loading) {
    return <div>loading</div>;
  }
  return (
    <>
      <img src={img} alt="" />
    </>
  );
}
