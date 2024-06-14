package nl.itvitae.buildachar.user;

import lombok.RequiredArgsConstructor;
import nl.itvitae.buildachar.ControllerRoutes;
import nl.itvitae.buildachar.exceptions.RestException;
import nl.itvitae.buildachar.role.RoleName;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ControllerRoutes.USER_ROUTE)
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @PostMapping("register")
  public ResponseEntity<UserDTO> register(@RequestBody AuthDTO authDTO) {
    if (authDTO.username() == null || authDTO.username().isBlank()) {
      throw new RestException(HttpStatus.BAD_REQUEST, "username is required");
    }
    if (authDTO.password() == null || authDTO.password().isBlank()) {
      throw new RestException(HttpStatus.BAD_REQUEST, "password is required");
    }

    User user = userService.save(authDTO.username(), authDTO.password(), RoleName.USER);

    return ResponseEntity.ok(UserDTO.from(user));
  }
}
