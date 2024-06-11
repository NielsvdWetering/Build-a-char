import React from "react";

export const DisplayField = ({ label, content }) => {
  return (
    <div className="input input-secondary">
      <span className="">{label}</span>
      <span>{content}</span>
    </div>
  );
};
