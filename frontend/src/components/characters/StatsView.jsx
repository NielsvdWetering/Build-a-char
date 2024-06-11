import React from "react";
import { IndividualStatView } from "./IndividualStatView";

export const StatsView = ({ stats }) => {
  return (
    <div className="grid w-full grid-cols-3 gap-5 rounded-md bg-secondary p-6">
      {Object.keys(stats).map((key) => (
        <IndividualStatView
          label={key}
          stat={stats[key]}
          key={`${key + stats}`} // kom ik op terug!! fix me!! forget me not!!
        />
      ))}
    </div>
  );
};
