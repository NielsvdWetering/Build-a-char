package nl.itvitae.buildachar.tool;

import java.util.List;
import lombok.RequiredArgsConstructor;
import nl.itvitae.buildachar.ControllerRoutes;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(ControllerRoutes.TOOL_ROUTE)
@CrossOrigin(origins = {"${app-cors}"})
public class ToolController {
  private final ToolService toolService;

  @GetMapping
  public List<ToolDTO> getAll() {
    return toolService.getAll().stream().map(ToolDTO::from).toList();
  }
}
