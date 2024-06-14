package nl.itvitae.buildachar.role;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "roles")
@NoArgsConstructor
@Getter
public class UserRole {
  @Id private String name;

  public UserRole(RoleName name) {
    this.name = name.name();
  }
}
