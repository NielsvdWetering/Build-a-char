package nl.itvitae.buildachar.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import nl.itvitae.buildachar.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {
  private static final String ROLES_CLAIM_NAME = "roles";

  private final SecretKey secretKey;
  private final Logger logger = LoggerFactory.getLogger(JwtService.class);

  @Value("${app.authentication.jwt-expiration-ms}")
  private int JWT_EXPIRATION_MS;

  public String generateTokenForUser(User user) {
    if (user == null) {
      throw new IllegalArgumentException("UserService.generateTokenForUser: user is null");
    }

    long currentTimeMillis = System.currentTimeMillis();

    return Jwts.builder()
        .claims(Map.of(ROLES_CLAIM_NAME, user.getAuthorities()))
        .subject(user.getUsername())
        .issuedAt(new Date(currentTimeMillis))
        .expiration(new Date(currentTimeMillis + JWT_EXPIRATION_MS))
        .signWith(secretKey)
        .compact();
  }

  public Optional<Claims> readClaimsFromToken(String token) {
    try {
      Claims claims =
          Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();

      return Optional.of(claims);
    } catch (RuntimeException ex) {
      logger.atDebug().log(
          "invalid bearer token detected: "
              + ex.getClass().getSimpleName()
              + " '"
              + ex.getMessage()
              + "'");

      return Optional.empty();
    }
  }

  public boolean isValidToken(String token) {
    return readClaimsFromToken(token).isPresent();
  }
}
