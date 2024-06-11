import React from "react";

export const TextField = ({ label, content }) => {
  return (
    <>
      <h6 className="">{label}</h6>
      <div className="textarea textarea-secondary">{content}</div>
    </>
  );
};
