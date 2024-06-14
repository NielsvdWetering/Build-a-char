package nl.itvitae.buildachar.user;

import lombok.RequiredArgsConstructor;
import nl.itvitae.buildachar.ControllerRoutes;
import nl.itvitae.buildachar.exceptions.RestException;
import nl.itvitae.buildachar.role.RoleName;
import nl.itvitae.buildachar.security.PasswordValidationResult;
import nl.itvitae.buildachar.security.PasswordValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ControllerRoutes.USER_ROUTE)
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;
  private final PasswordValidator passwordValidator;

  private static final String INVALID_USERNAME_PASSWORD_ERROR_MESSAGE =
      "username or password is invalid";

  @PostMapping("register")
  public ResponseEntity<UserDTO> register(@RequestBody AuthDTO authDTO) {
    if (authDTO.username() == null || authDTO.username().isBlank()) {
      throw new RestException(HttpStatus.BAD_REQUEST, "username is required");
    }
    if (authDTO.password() == null || authDTO.password().isBlank()) {
      throw new RestException(HttpStatus.BAD_REQUEST, "password is required");
    }

    if (userService.findUserByUsername(authDTO.username()).isPresent()) {
      throw new RestException(HttpStatus.BAD_REQUEST, INVALID_USERNAME_PASSWORD_ERROR_MESSAGE);
    }

    PasswordValidationResult result = passwordValidator.validate(authDTO.password());
    if (!result.isValid()) {
      throw new RestException(HttpStatus.BAD_REQUEST, String.join(";", result.errors()));
    }

    User user = userService.save(authDTO.username(), authDTO.password(), RoleName.USER);

    return ResponseEntity.ok(UserDTO.from(user));
  }
}
