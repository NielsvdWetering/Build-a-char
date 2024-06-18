import React, { useEffect, useState } from "react";
import NavbarButton from "./NavbarButton";
import { useNavigate } from "react-router-dom";
import { useAuthentication } from "../../hooks";
import { BeatLoader } from "react-spinners";
import NavbarItemsContainer from "./subcomponents/NavbarItemsContainer";

export default function Navbar({}) {
  const navigate = useNavigate();
  const { logout, isLoggedIn } = useAuthentication();
  const [loggedIn, setLoggedIn] = useState(null);

  isLoggedIn().then((response) => {
    if (response !== loggedIn) {
      setLoggedIn(response);
    }
  });

  return (
    <>
      <div className="navbar flex justify-between bg-primary">
        <NavbarItemsContainer id="buttons" className="navbar-start">
          <h1 className="px-6 text-2xl font-semibold text-primary-content">
            Build-a-char
          </h1>
          <NavbarButton title="Home" onClick={() => navigate("/")} />
          <NavbarButton title="Creator" onClick={() => navigate("/creator")} />
          <NavbarButton
            title="Characters"
            onClick={() => navigate("/characters")}
          />
          {loggedIn && (
            <NavbarButton
              title="My Characters"
              onClick={() => navigate("/my-characters")}
            />
          )}
        </NavbarItemsContainer>

        <NavbarItemsContainer className="navbar-end mr-4">
          <label className="flex cursor-pointer gap-2">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width="20"
              height="20"
              viewBox="0 0 24 24"
              fill="none"
              stroke="currentColor"
              strokeWidth="2"
              strokeLinecap="round"
              strokeLinejoin="round"
            >
              <circle cx="12" cy="12" r="5" />
              <path d="M12 1v2M12 21v2M4.2 4.2l1.4 1.4M18.4 18.4l1.4 1.4M1 12h2M21 12h2M4.2 19.8l1.4-1.4M18.4 5.6l1.4-1.4" />
            </svg>
            <input
              type="checkbox"
              value="buildachardark"
              className="theme-controller toggle"
            />
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width="20"
              height="20"
              viewBox="0 0 24 24"
              fill="none"
              stroke="currentColor"
              strokeWidth="2"
              strokeLinecap="round"
              strokeLinejoin="round"
            >
              <path d="M21 12.79A9 9 0 1 1 11.21 3 7 7 0 0 0 21 12.79z"></path>
            </svg>
          </label>
          {loggedIn === null ? (
            <BeatLoader />
          ) : (
            <NavbarButton
              title={loggedIn ? "Log out" : "Log in"}
              onClick={() => {
                if (loggedIn) {
                  navigate("/");
                  logout();
                  return;
                }

                navigate("/login");
              }}
            />
          )}
        </NavbarItemsContainer>
      </div>
    </>
  );
}
