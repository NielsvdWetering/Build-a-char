import React from "react";

export const Error = ({ msg }) => {
  return (
    <div className="rounded border-2 border-accent p-4 text-center text-xl font-bold text-accent">
      {msg}
    </div>
  );
};
