import React from "react";
import NavbarButton from "./NavbarButton";
import { useNavigate } from "react-router-dom";

export default function Navbar({}) {
  const navigate = useNavigate();

  return (
    <>
      <div className="navbar bg-primary">
        <h1 className="px-6 text-2xl font-semibold text-primary-content">
          Build-a-char
        </h1>
        <NavbarButton title="Home" onClick={() => navigate("/")} />
        <NavbarButton title="Creator" onClick={() => navigate("/creator")} />
        <NavbarButton
          title="Characters"
          onClick={() => navigate("/characters")}
        />
        <input
          type="checkbox"
          value="buildacharlight"
          className="theme-controller checkbox"
        />
      </div>
    </>
  );
}
