import React from "react";

export const TextField = ({ label, content }) => {
  return (
    <div>
      <h6>{label}</h6>
      <div>{content}</div>
    </div>
  );
};
