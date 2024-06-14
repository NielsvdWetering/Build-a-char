package nl.itvitae.buildachar.user;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import nl.itvitae.buildachar.role.RoleName;
import nl.itvitae.buildachar.role.RoleRepository;
import nl.itvitae.buildachar.security.PasswordValidationResult;
import nl.itvitae.buildachar.security.PasswordValidator;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;
  private final PasswordValidator passwordValidator;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository
        .findByUsername(username)
        .orElseThrow(
            () -> new UsernameNotFoundException("UserService.loadUserByUsername: user not found"));
  }

  public Optional<User> findUserByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  public List<User> getAll() {
    return userRepository.findAll();
  }

  public User save(String username, String password, RoleName role) {
    if (userRepository.findByUsername(username).isPresent()) {
      throw new RuntimeException("UserService.save: user with username already exists");
    }

    PasswordValidationResult result = passwordValidator.validate(password);
    if (!result.isValid()) {
      throw new RuntimeException("password is invalid");
    }

    return userRepository.save(
        new User(
            username,
            passwordEncoder.encode(password),
            roleRepository
                .findById(role.name())
                .orElseThrow(
                    () ->
                        new RuntimeException(
                            "UserService.save: UserRole '" + role.name() + "' does not exist"))));
  }
}
