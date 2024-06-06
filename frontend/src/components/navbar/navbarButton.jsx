import React from "react";

export default function NavbarButton({ title, onClick }) {
  return (
    <>
      <div
        className="btn btn-ghost text-primary-content mx-2 text-xl"
        onClick={onClick}
      >
        {title}
      </div>
    </>
  );
}
