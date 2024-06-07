package nl.itvitae.buildachar.weapon;

import java.util.UUID;

public record WeaponDTO(UUID id, String name, WeaponType weaponType, double attackPower) {
  public static WeaponDTO from(Weapon weapon) {
    return new WeaponDTO(
        weapon.getId(), weapon.getName(), weapon.getWeaponType(), weapon.getAttackPower());
  }
}
