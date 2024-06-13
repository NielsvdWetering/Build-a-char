export default function WeaponSelect({
  defaultValue,
  weapons,
  setSelectedWeapon,
}) {
  const description = "Select a weapon";

  return (
    <>
      <select
        className="select select-primary w-full"
        onChange={(event) => setSelectedWeapon(JSON.parse(event.target.value))}
        defaultValue={defaultValue ? JSON.stringify(defaultValue) : description}
      >
        <option disabled value={description}>
          {description}
        </option>
        {weapons.map((weapon) => (
          <option key={weapon.id} value={JSON.stringify(weapon)}>
            {weapon.name}
          </option>
        ))}
      </select>
    </>
  );
}
