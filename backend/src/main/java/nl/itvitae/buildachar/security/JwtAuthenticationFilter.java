package nl.itvitae.buildachar.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import nl.itvitae.buildachar.user.User;
import nl.itvitae.buildachar.user.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private final UserService userService;
  private final JwtService jwtService;

  private static final String AUTHORIZATION_HEADER_JWT_PREFIX = "Bearer ";

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    getUserIfAuthenticated(request)
        .ifPresent(
            principal -> {
              UsernamePasswordAuthenticationToken authToken =
                  new UsernamePasswordAuthenticationToken(
                      principal, null, principal.getAuthorities());
              authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

              SecurityContextHolder.getContext().setAuthentication(authToken);
            });

    filterChain.doFilter(request, response);
  }

  private Optional<User> getUserIfAuthenticated(HttpServletRequest request) {
    return Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION))
        .filter(authorization -> authorization.startsWith(AUTHORIZATION_HEADER_JWT_PREFIX))
        .flatMap(
            authorization ->
                jwtService.readClaimsFromToken(
                    authorization.substring(AUTHORIZATION_HEADER_JWT_PREFIX.length())))
        .filter(claims -> claims.getExpiration().after(new Date()))
        .flatMap(claims -> userService.findUserByUsername(claims.getSubject()));
  }
}
