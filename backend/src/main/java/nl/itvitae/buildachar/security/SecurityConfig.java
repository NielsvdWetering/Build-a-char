package nl.itvitae.buildachar.security;

import io.jsonwebtoken.Jwts;
import javax.crypto.SecretKey;
import nl.itvitae.buildachar.ControllerRoutes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.httpBasic(AbstractHttpConfigurer::disable)
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(
            httpRequest -> {
              httpRequest.requestMatchers(ControllerRoutes.USER_ROUTE + "/**").permitAll();
              httpRequest.requestMatchers(/*HttpMethod.GET, */ "/**").permitAll();
              httpRequest.anyRequest().authenticated();
            })
        .build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Bean
    public SecretKey secretKey(){
      return Jwts.SIG.HS256.key().build();
  }
}
