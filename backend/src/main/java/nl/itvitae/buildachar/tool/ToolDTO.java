package nl.itvitae.buildachar.tool;

import java.util.UUID;

public record ToolDTO(UUID id, String name, String description) {
  public static ToolDTO from(Tool tool) {
    return new ToolDTO(tool.getId(), tool.getName(), tool.getDescription());
  }
}
