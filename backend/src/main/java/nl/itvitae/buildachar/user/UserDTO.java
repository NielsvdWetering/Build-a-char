package nl.itvitae.buildachar.user;

public record UserDTO(String username) {
  public static UserDTO from(User user) {
    return new UserDTO(user.getUsername());
  }
}
