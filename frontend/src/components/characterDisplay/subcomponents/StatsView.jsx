import React from "react";
import { IndividualStatView } from "./IndividualStatView";
import { Error } from "../../generic/Error";

export const StatsView = ({ stats }) => {
  if (!stats) return <Error msg={"Could not load stats"} />;

  return (
    <div className="grid aspect-video w-full grid-cols-3 gap-5 rounded-md bg-primary p-6 text-primary-content shadow-custom-dark">
      {Object.keys(stats).map((key, i) => (
        <IndividualStatView label={key} stat={stats[key]} key={`${key + i}`} />
      ))}
    </div>
  );
};
