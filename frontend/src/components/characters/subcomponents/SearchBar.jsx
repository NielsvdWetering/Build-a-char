import React, { useState, useEffect } from "react";
import useApi from "../../../hooks/useApi";
import { useNavigate } from "react-router-dom";

export const SearchBar = () => {
  const [searchParams, setSearchParams] = useState("");
  const [characters, setCharacters] = useState(null);
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
    if (searchParams) {
      setUrl(`characters/find?search=${searchParams}`);
      fetchCharacters();
    }
  }, [searchParams]);

  const showSearchResults = () => {};

  return (
    <div className="relative w-2/3">
      <input
        className={"input input-primary w-full text-center"}
        onChange={(e) => {
          setSearchParams(e.target.value);
        }}
        placeholder={"Search..."}
      />
      <div className={`absolute w-full border-2 bg-white`}>
        {characters && (
          <ul>
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
