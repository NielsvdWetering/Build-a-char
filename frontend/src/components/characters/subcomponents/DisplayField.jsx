import React from "react";

export const DisplayField = ({ label, content }) => {
  return (
    <>
      <div className="grid grid-cols-2 capitalize text-secondary-content">
        <span className="font-bold">{label}</span>
        <span>{content}</span>
      </div>
    </>
  );
};
