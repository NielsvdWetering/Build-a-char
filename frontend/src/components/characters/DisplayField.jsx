import React from "react";

export const DisplayField = ({ label, content }) => {
  return (
    <div className="flex justify-between tracking-wide">
      <h6>{label}</h6>
      <p>{content}</p>
    </div>
  );
};
