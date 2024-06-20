import { RadioButton } from "./RadioButton";

export const Filter = ({ handleOnCheck }) => {
  return (
    <div className="space-evenly my-8 grid w-fit rounded-lg border-4 border-double border-primary p-8">
      <form>
        <RadioButton label={"All characters"} handleOnCheck={handleOnCheck} />
        <RadioButton label={"My characters"} handleOnCheck={handleOnCheck} />
      </form>
    </div>
  );
};
