import React from "react";

export const IndividualStatView = ({ label, stat }) => {
  return (
    <div className="m-0.5 grid w-full grid-cols-1 rounded bg-accent p-0.5">
      <span className="accent-content text-center md:font-bold">
        {label.replace("base", "")}
      </span>
      <span className="accent-content text-center">{stat}</span>
    </div>
  );
};
