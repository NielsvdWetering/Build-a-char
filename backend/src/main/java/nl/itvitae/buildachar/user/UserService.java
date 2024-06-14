package nl.itvitae.buildachar.user;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import nl.itvitae.buildachar.role.RoleName;
import nl.itvitae.buildachar.role.RoleRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository
        .findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("user not found"));
  }

  public Optional<User> findUserByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  public List<User> getAll() {
    return userRepository.findAll();
  }

  public User save(String username, String password, RoleName role) {
    return userRepository.save(
        new User(
            username,
            password,
            roleRepository
                .findById(role.name())
                .orElseThrow(
                    () -> new RuntimeException("UserRole '" + role.name() + "' does not exist"))));
  }
}
