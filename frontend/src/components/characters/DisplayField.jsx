import React from "react";

export const DisplayField = ({ label, content }) => {
  return (
    <>
      <h6 className="">{label}</h6>
      <p className="input input-secondary">{content}</p>
    </>
  );
};
