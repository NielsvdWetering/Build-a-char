import React from "react";

export const IndividualStatView = ({ label, stat }) => {
  return (
    <div className="m-0.5 grid w-full grid-cols-1 rounded border-2 bg-accent p-0.5">
      <span className="accent-content border-b text-center">{label}</span>
      <span className="accent-content text-center">{stat}</span>
    </div>
  );
};
