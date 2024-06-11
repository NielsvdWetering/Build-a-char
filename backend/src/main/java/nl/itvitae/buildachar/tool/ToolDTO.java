package nl.itvitae.buildachar.tool;


public record ToolDTO(String id, String name, String description) {
  public static ToolDTO from(Tool tool) {
    return new ToolDTO(tool.getId().toString(), tool.getName(), tool.getDescription());
  }
}
