import StatTooltip from "./StatTooltip";
import TextTooltip from "./TextTooltip";

export default function WeaponTooltip({ armor }) {
  return (
    <div className="w-48">
      <StatTooltip label="Armor type:" value={armor.type} />
      <StatTooltip label="Armor class:" value={armor.armorClass} />
      <StatTooltip label="Defence:" value={armor.defence} />
      <TextTooltip text={armor.description} />
    </div>
  );
}
