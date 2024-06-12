import React from "react";
import { IndividualStatView } from "./IndividualStatView";

export const StatsView = ({ stats }) => {
  return (
    <div className="grid aspect-video w-full grid-cols-3 gap-5 rounded-md bg-primary p-6 text-primary-content">
      {Object.keys(stats).map((key, i) => (
        <IndividualStatView label={key} stat={stats[key]} key={`${key + i}`} />
      ))}
    </div>
  );
};
