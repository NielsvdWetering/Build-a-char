import { CharacterImage } from "../../characterDisplay/subcomponents/CharacterImage";
import { DisplayField } from "./DisplayField";
import img from "/cutebear.png";
export const Card = ({ character, onClick }) => {
  return (
    <div
      onClick={onClick}
      className="hover:bg-primary-hover cursor-pointer rounded bg-primary p-2 shadow-custom-dark"
    >
      {character && (
        <div className="flex flex-row">
          <div className="w-52 p-4">
            <img
              src={
                character.characterPictureId != null
                  ? `http://localhost:8080/api/v1/characters/image/${character.characterPictureId}`
                  : img
              }
              className="rounded-lg"
            />
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
