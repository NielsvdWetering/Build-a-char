package nl.itvitae.buildachar.weapon;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class WeaponService {
  private WeaponRepository weaponRepository;

  public WeaponService(WeaponRepository weaponRepository) {
    this.weaponRepository = weaponRepository;
  }

  public List<Weapon> getAll() {
    return weaponRepository.findAll();
  }

  public Optional<Weapon> getById(UUID id) {
    return weaponRepository.findById(id);
  }

  public void save(String name, String description, WeaponType weaponType, Double attackPower) {
    weaponRepository.save(new Weapon(name, description, weaponType, attackPower));
  }

  public void update(Weapon weapon) {
    if (weapon == null) {
      throw new IllegalArgumentException("WeaponService.update: weapon is null");
    }

    weaponRepository.save(weapon);
  }
}
