import Navbar from "./components/navbar/Navbar";
import Home from "./components/home/Home";
import CharacterCreator from "./components/editor/CharacterCreator";
import Characters from "./components/characters/Characters";
import CharacterDisplay from "./components/characterDisplay/CharacterDisplay";
import CharacterPatcher from "./components/editor/CharacterPatcher";

import { Routes, Route } from "react-router-dom";
import Register from "./components/auth/Register";

export default function App() {
  return (
    <>
      <div id="page" className="flex h-screen w-screen flex-col">
        <Navbar />

        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/creator" element={<CharacterCreator />} />
          <Route path="/characters" element={<Characters />} />
          <Route path="/characters/:id" element={<CharacterDisplay />} />
          <Route path="/characters/:id/edit" element={<CharacterPatcher />} />
          <Route path="/register" element={<Register />} />
        </Routes>
      </div>
    </>
  );
}
