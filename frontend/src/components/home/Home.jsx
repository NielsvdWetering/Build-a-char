import React from "react";
import CharacterCreator from "../editor/CharacterCreator";
import { useState, useEffect } from "react";
import { useApi } from "../../hooks";

export default function Home() {
  return (
    <>
      <CharacterCreator />
    </>
  );
}
