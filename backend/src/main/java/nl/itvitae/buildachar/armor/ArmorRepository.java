package nl.itvitae.buildachar.armor;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ArmorRepository extends JpaRepository<Armor, UUID> {}
