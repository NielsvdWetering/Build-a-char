export default function WeaponSelect({ weapons, setSelectedWeapon }) {
  const defaultValue = "Select a weapon";

  return (
    <>
      <select
        className="select select-secondary w-full"
        onChange={(event) => setSelectedWeapon(JSON.parse(event.target.value))}
        defaultValue={defaultValue}
      >
        <option disabled value={defaultValue}>
          {defaultValue}
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
