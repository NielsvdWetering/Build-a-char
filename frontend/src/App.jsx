import Navbar from "./components/navbar/Navbar";
import Home from "./components/home/Home";
import CharacterCreator from "./components/editor/CharacterCreator";
import Characters from "./components/characters/Characters";
import CharacterDisplay from "./components/characterDisplay/CharacterDisplay";
import CharacterPatcher from "./components/editor/CharacterPatcher";

import { Routes, Route, Navigate } from "react-router-dom";

import RegisterPage from "./components/auth/register/RegisterPage";
import LoginPage from "./components/auth/login/LoginPage";

export default function App() {
  document.querySelector(":root").style.setProperty("--navbar-height", "64px");
  console.log(
    getComputedStyle(document.querySelector(":root")).getPropertyValue(
      "--navbar-height",
    ),
  );

  return (
    <>
      <div id="page" className="flex h-screen w-screen flex-col">
        <Navbar />

        <div className={`h-[calc(100%_-_var(--navbar-height))]`}>
          <Routes>
            <Route path="/" element={<Navigate to={"/creator"} />} />
            <Route path="/creator" element={<CharacterCreator />} />
            <Route path="/characters" element={<Characters />} />
            <Route
              path="/my-characters"
              element={<Characters ownedOnly myCharacters />}
            />
            <Route path="/characters/:id" element={<CharacterDisplay />} />
            <Route path="/characters/:id/edit" element={<CharacterPatcher />} />
            <Route path="/register" element={<RegisterPage />} />
            <Route path="/login" element={<LoginPage />} />
          </Routes>
        </div>
      </div>
    </>
  );
}
