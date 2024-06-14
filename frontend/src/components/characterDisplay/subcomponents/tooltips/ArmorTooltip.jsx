import StatTooltip from "./StatTooltip";
import TextTooltip from "./TextTooltip";

export default function WeaponTooltip({ armor }) {
  return (
    <div className="flex w-48 flex-col gap-2">
      <div>
        <StatTooltip label="Armor type:" value={armor.type} />
        <StatTooltip label="Armor class:" value={armor.armorClass} />
        <StatTooltip label="Defence:" value={armor.defence} />
      </div>
      <TextTooltip text={armor.description} />
    </div>
  );
}
