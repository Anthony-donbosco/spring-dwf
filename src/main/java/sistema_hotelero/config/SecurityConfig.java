
package sistema_hotelero.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import sistema_hotelero.security.CustomUserDetailsService;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            CustomUserDetailsService userDetailsService) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .userDetailsService(userDetailsService)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/",
                    "/index.html",
                    "/swagger-ui/**",
                    "/v3/api-docs/**"
                ).permitAll()

                .requestMatchers(HttpMethod.GET, "/api/pagos")
                .hasRole("ADMIN")

                .requestMatchers("/api/**")
                .hasAnyRole("ADMIN", "RECEPCIONISTA")

                .anyRequest().permitAll()
            )
            .httpBasic(basic -> {});

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
