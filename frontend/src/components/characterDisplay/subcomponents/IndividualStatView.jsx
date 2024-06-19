import React from "react";

export const IndividualStatView = ({ label, stat }) => {
  return (
    <div className="m-0.5 grid w-full grid-cols-1 rounded bg-secondary p-0.5">
      <span className="text-center text-secondary-content underline md:font-bold">
        {label.replace("base", "").substring(0, 3).toUpperCase()}
      </span>
      <span className="text-center text-5xl text-secondary-content">
        {stat}
      </span>
    </div>
  );
};
