package nl.itvitae.buildachar.weapon;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class WeaponService {
  private WeaponRepository weaponRepository;

  public List<Weapon> getAll() {
    return weaponRepository.findAll();
  }

  public Optional<Weapon> getById(UUID id) {
    return weaponRepository.findById(id);
  }

  public void save(Weapon weapon) {
    weaponRepository.save(weapon);
  }
}
