import StatTooltip from "./StatTooltip";

export default function WeaponTooltip({ type, attackPower }) {
  return (
    <div>
      <StatTooltip label="Weapon type:" value={type} />
      <StatTooltip label="Attack power:" value={attackPower} />
    </div>
  );
}
