import { DisplayField } from "./DisplayField";
import img from "/cutebear.png";
export const Card = ({ character, onClick }) => {
  return (
    <div
      onClick={onClick}
      className="cursor-pointer rounded bg-primary p-2 shadow-custom-dark hover:bg-primary-hover"
    >
      {character && (
        <div className="flex flex-row">
          <div className="w-52 p-4">
            <img src={img} className="rounded-lg" />
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
