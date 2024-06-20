import React, { useState, useEffect } from "react";
import useApi from "../../../hooks/useApi";
import { useNavigate } from "react-router-dom";

export const SearchBar = ({ detectClick }) => {
  const [searchParams, setSearchParams] = useState("");
  const [characters, setCharacters] = useState([]);
  const [url, setUrl] = useState("characters");
  const navigate = useNavigate();
  const { get } = useApi();

  const fetchCharacters = () => {
    get(url)
      .then(setCharacters)
      .catch((error) => {
        console.error("There was an error fetching the characters!", error);
      });
  };

  useEffect(() => {
    // if a click outside of the component was detected, reset searchParams so that the box will be set to hidden
    setSearchParams("");
  }, [detectClick]);

  useEffect(() => {
    fetchCharacters();
  }, [url]);

  const showSearchResults = (searchValue) => {
    setSearchParams(searchValue);
    if (searchValue !== "") {
      setUrl(`characters?search=${searchValue}`);
    } else {
      setUrl("characters");
    }
  };

  return (
    <div className="relative w-2/3">
      <input
        id="search-bar"
        className={"input input-primary w-full"}
        onChange={(e) => {
          if (e.target.value === "") {
            setCharacters([]);
          } else {
            showSearchResults(e.target.value);
          }
        }}
        onFocus={() => {
          setCharacters([]);
        }}
        placeholder={"Search..."}
      />
      <div
        id="result-box"
        className={`absolute w-full rounded-b-lg bg-base-100 p-4 text-primary-content ${searchParams !== "" ? "" : "hidden"}`}
      >
        {characters && (
          <ul>
            {characters.map((character) => (
              <li
                className="m-2 cursor-pointer border-b-2 pt-2 font-bold"
                key={character.id}
                onClick={() => {
                  navigate("/characters/" + character.id);
                }}
              >
                {character.name}
              </li>
            ))}
          </ul>
        )}
      </div>
    </div>
  );
};
