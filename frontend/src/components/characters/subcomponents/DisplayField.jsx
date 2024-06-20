import React from "react";

export const DisplayField = ({ content }) => {
  return (
    <>
      <div className="rounded bg-secondary p-2 capitalize text-secondary-content">
        <span className="p-2 font-bold">{content}</span>
      </div>
    </>
  );
};
