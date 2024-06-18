import { RadioButton } from "./RadioButton";

export const Sort = ({ handleOnCheck }) => {
  return (
    <div className="space-evenly grid w-fit rounded-lg border-2 p-4">
      <form>
        <RadioButton label={"All characters"} handleOnCheck={handleOnCheck} />
        <RadioButton label={"My characters"} handleOnCheck={handleOnCheck} />
        <RadioButton
          label={"My Favorite characters"}
          handleOnCheck={handleOnCheck}
        />
      </form>
    </div>
  );
};
