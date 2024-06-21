import React from "react";

export const RadioButton = ({ label, handleOnCheck }) => {
  return (
    <div className="py-2">
      <input
        type="radio"
        name={label.replace(" ", "-").toLowerCase()}
        onChange={() => {
          handleOnCheck(label);
        }}
      />
      <label className="ml-2" htmlFor={label.replace(" ", "-").toLowerCase()}>
        {label}
      </label>
    </div>
  );
};
