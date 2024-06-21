import React from "react";

export const CharacterDescription = ({ description, className }) => {
  return (
    <div
      className={`body card w-full rounded-md bg-primary p-4 shadow-custom-dark ${className}`}
    >
      {description && <div className="card-title">Description</div>}
      <span className="text-primary-content">
        {description ? description : "No description"}
        <p>
          Lorem ipsum dolor sit amet, consectetur adipiscing elit,
          <br />
          sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
          <br />
          Ut enim ad minim veniam, quis nostrud exercitation
          <br />
          ullamco laboris nisi ut aliquip ex ea commodo consequat.
          <br />
          Duis aute irure dolor in reprehenderit in voluptate velit esse cillum
          dolore eu fugiat nulla pariatur.
          <br />
          Excepteur sint occaecat cupidatat non proident,
          <br />
          sunt in culpa qui officia deserunt mollit anim id est laborum.
        </p>
        <p>
          Lorem ipsum dolor sit amet, consectetur adipiscing elit,
          <br />
          sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
          <br />
          Ut enim ad minim veniam, quis nostrud exercitation
          <br />
          ullamco laboris nisi ut aliquip ex ea commodo consequat.
          <br />
          Duis aute irure dolor in reprehenderit in voluptate velit esse cillum
          dolore eu fugiat nulla pariatur.
          <br />
          Excepteur sint occaecat cupidatat non proident,
          <br />
          sunt in culpa qui officia deserunt mollit anim id est laborum.
        </p>
        <p>
          Lorem ipsum dolor sit amet, consectetur adipiscing elit,
          <br />
          sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
          <br />
          Ut enim ad minim veniam, quis nostrud exercitation
          <br />
          ullamco laboris nisi ut aliquip ex ea commodo consequat.
          <br />
          Duis aute irure dolor in reprehenderit in voluptate velit esse cillum
          dolore eu fugiat nulla pariatur.
          <br />
          Excepteur sint occaecat cupidatat non proident,
          <br />
          sunt in culpa qui officia deserunt mollit anim id est laborum.
        </p>
        <p>
          Lorem ipsum dolor sit amet, consectetur adipiscing elit,
          <br />
          sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
          <br />
          Ut enim ad minim veniam, quis nostrud exercitation
          <br />
          ullamco laboris nisi ut aliquip ex ea commodo consequat.
          <br />
          Duis aute irure dolor in reprehenderit in voluptate velit esse cillum
          dolore eu fugiat nulla pariatur.
          <br />
          Excepteur sint occaecat cupidatat non proident,
          <br />
          sunt in culpa qui officia deserunt mollit anim id est laborum.
        </p>
      </span>
    </div>
  );
};
