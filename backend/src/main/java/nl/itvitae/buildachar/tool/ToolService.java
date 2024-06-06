package nl.itvitae.buildachar.tool;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ToolService {
  private ToolRepository toolRepository;

  public List<Tool> getAll() {
    return toolRepository.findAll();
  }

  public Optional<Tool> findById(UUID id) {
    return toolRepository.findById(id);
  }

  public void save(String name, String description) {
    toolRepository.save(new Tool(name, description));
  }
}
