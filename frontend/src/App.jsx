import "./App.css";
import Navbar from "./components/navbar/navbar";
import Home from "./components/home/home";
import Creator from "./components/creator/creator";
import Characters from "./components/characters/characters";


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
        </Routes>
      </div>
    </>
  );
}
