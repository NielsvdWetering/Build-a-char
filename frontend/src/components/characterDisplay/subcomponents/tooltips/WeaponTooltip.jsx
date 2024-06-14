import StatTooltip from "./StatTooltip";

export default function WeaponTooltip({ type, attackPower }) {
  return (
    <div className="w-44">
      <StatTooltip label="Weapon type:" value={type} />
      <StatTooltip label="Attack power:" value={attackPower} />
    </div>
  );
}
