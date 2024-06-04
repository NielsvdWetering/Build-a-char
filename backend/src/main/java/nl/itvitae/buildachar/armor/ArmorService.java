package nl.itvitae.buildachar.armor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArmorService {
    private final ArmorRepository armorRepository;

    public List<Armor> getAll(){
        return armorRepository.findAll();
    }

    public Optional<Armor> getById(UUID id){
        return armorRepository.findById(id);
    }

    public Armor save(String name, String description, double defence, ArmorType armorType, ArmorClass armorClass){
        return armorRepository.save(
                new Armor(name, description,defence,armorType,armorClass));
    }
}
