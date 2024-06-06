import React, { useEffect, useState } from "react";
import CreatorColumn from "./creatorColumn";
import RaceSelect from "./subcomponents/raceSelect";
import axios from "axios";

export default function Creator() {
  const [selectedRace, setSelectedRace] = useState(null);
  const [races, setRaces] = useState([]);

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/v1/races")
      .catch(console.error)
      .then((response) => setRaces(response.data));
  }, []);

  useEffect(() => {
    if (!races || races === null || races.length === 0) {
      return;
    }

    setSelectedRace(races[1]);
  }, [races]);

  return (
    <>
      <div id="page" className="flex h-full justify-around">
        <CreatorColumn>
          <RaceSelect races={races} setSelectedRace={setSelectedRace} />
        </CreatorColumn>
        <CreatorColumn>content in the 2nd column</CreatorColumn>
        <CreatorColumn>content in the 3th column</CreatorColumn>
      </div>
    </>
  );
}
