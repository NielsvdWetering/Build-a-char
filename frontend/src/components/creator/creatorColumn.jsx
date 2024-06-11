import React from "react";

export default function CreatorColumn({ children }) {
  return (
    <>
      <div className="flex h-full flex-grow basis-0 flex-col justify-start p-10">
        {children}
      </div>
    </>
  );
}
