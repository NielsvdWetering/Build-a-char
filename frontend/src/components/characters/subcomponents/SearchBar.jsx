import React, { useState, useEffect } from "react";
import useApi from "../../../hooks/useApi";
import { useNavigate } from "react-router-dom";

export const SearchBar = () => {
  const [searchParams, setSearchParams] = useState("");
  const [characters, setCharacters] = useState([]);
  const [url, setUrl] = useState("characters");
  const { get } = useApi();
  const navigate = useNavigate();

  const fetchCharacters = () => {
    get(url)
      .then(setCharacters)
      .catch((error) => {
        console.error("There was an error fetching the characters!", error);
      });
  };

  useEffect(() => {
    fetchCharacters();
  }, [url]);

  const showSearchResults = (searchValue) => {
    setSearchParams(searchValue);
    if (searchValue !== "") {
      setUrl(`characters/find?search=${searchValue}`);
    } else {
      setUrl("characters");
    }
  };

  return (
    <div className="relative w-2/3">
      <input
        className={"input input-primary w-full text-center"}
        onChange={(e) => {
          showSearchResults(e.target.value);
        }}
        placeholder={"Search..."}
      />
      <div className={`absolute w-full bg-white`}>
        {characters && (
          <ul className={searchParams === "" ? "hidden" : ""}>
            {characters.map((character) => (
              <li
                className="m-2 cursor-pointer border-b-2 pt-2 font-bold hover:bg-slate-50"
                key={character.id}
                onClick={() => navigate("/characters/" + character.id)}
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
