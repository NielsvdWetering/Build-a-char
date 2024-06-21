import { GiInfo } from "react-icons/gi";
import { Tooltip } from "react-tooltip";

export default function CharacterItem({ item, tooltip }) {
  return (
    <>
      <Tooltip id="my-tooltip" />
      <div className="my-1 flex w-full items-center justify-between rounded-lg bg-secondary px-4 py-2 text-secondary-content">
        <span>{item}</span>
        <div
          className="text-xl"
          data-tooltip-id="my-tooltip"
          data-tooltip-html={tooltip}
        >
          <GiInfo />
        </div>
      </div>
    </>
  );
}
