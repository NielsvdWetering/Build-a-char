package nl.itvitae.buildachar.security;

import nl.itvitae.buildachar.ControllerRoutes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
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
              httpRequest
                  .requestMatchers(HttpMethod.GET, ControllerRoutes.CHARACTER_ROUTE + "/**")
                  .permitAll();
              httpRequest.anyRequest().authenticated();
            })
        .build();
  }
}
