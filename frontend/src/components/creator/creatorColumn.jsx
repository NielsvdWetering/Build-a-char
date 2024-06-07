import React from "react";

export default function CreatorColumn({ children }) {
  return (
    <>
      <div className="flex h-full flex-grow flex-col justify-start p-10">
        {children}
      </div>
    </>
  );
}
