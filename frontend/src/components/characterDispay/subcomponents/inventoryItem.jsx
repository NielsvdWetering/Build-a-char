import { GiInfo } from "react-icons/gi";
export default function InventoryItem({ inventoryItem, tooltip }) {
  return (
    <>
      <div className="my-2 flex w-full items-center justify-between rounded-lg bg-secondary px-4 py-2 text-secondary-content">
        <span>{inventoryItem}</span>
        <div className="tooltip text-xl" data-tip={tooltip}>
          <GiInfo />
        </div>
      </div>
    </>
  );
}
