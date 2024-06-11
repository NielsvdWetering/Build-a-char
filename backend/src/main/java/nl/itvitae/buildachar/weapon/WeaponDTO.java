package nl.itvitae.buildachar.weapon;


public record WeaponDTO(String id, String name, WeaponType weaponType, double attackPower) {
  public static WeaponDTO from(Weapon weapon) {
    return new WeaponDTO(
        weapon.getId().toString(), weapon.getName(), weapon.getWeaponType(), weapon.getAttackPower());
  }
}
