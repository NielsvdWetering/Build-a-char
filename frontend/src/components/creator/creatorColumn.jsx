import React from "react";

export default function CreatorColumn({ children }) {
  return (
    <>
      <div className="flex h-full flex-col justify-start pt-10">{children}</div>
    </>
  );
}
