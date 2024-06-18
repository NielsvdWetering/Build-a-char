import { DisplayField } from "./DisplayField";
import img from "/viking_color.png";
export const Card = ({ character, onClick }) => {
  return (
    <div
      onClick={onClick}
      className="hover:bg-primary-hover cursor-pointer rounded bg-primary p-2 shadow-custom-dark"
    >
      {character && (
        <div className="flex flex-row">
          <div className="w-1/3">
            <img src={img} />
          </div>
          <div className="flex w-2/3 flex-col justify-between py-6 pl-4 pr-2">
            <DisplayField content={character.name} />
            <DisplayField content={character.race} />
            <DisplayField content={character.characterClass} />
          </div>
        </div>
      )}
    </div>
  );
};
