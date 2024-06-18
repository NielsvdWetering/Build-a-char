import React from "react";

export default function PageColumn({ children, className }) {
  return (
    <>
      <div
        className={
          className + " flex h-full flex-grow basis-0 flex-col gap-10 p-10"
        }
      >
        {children}
      </div>
    </>
  );
}
