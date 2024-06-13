import Navbar from "./components/navbar/Navbar";
import Home from "./components/home/Home";
import Creator from "./components/creator/Creator";
import Characters from "./components/characters/Characters";
import CharacterDisplay from "./components/characterDispay/CharacterDisplay";

import { Routes, Route } from "react-router-dom";

export default function App() {
  return (
    <>
      <div id="page" className="flex h-screen w-screen flex-col">
        <Navbar />

        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/creator" element={<Creator />} />
          <Route path="/characters" element={<Characters />} />
          <Route path="/characters/:id" element={<CharacterDisplay />} />
        </Routes>
      </div>
    </>
  );
}
