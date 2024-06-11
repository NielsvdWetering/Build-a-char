import React from "react";

export const DisplayField = ({ label, content }) => {
  return (
    <>
      <div className="grid grid-cols-2">
        <span>{label}</span>
        <span>{content}</span>
      </div>
    </>
  );
};
