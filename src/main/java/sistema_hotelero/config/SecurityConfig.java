
package sistema_hotelero.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.AuthenticationEntryPoint; 
import sistema_hotelero.security.CustomUserDetailsService;
import sistema_hotelero.security.JwtAuthenticationFilter;
import jakarta.servlet.http.HttpServletResponse;
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            CustomUserDetailsService userDetailsService,
            JwtAuthenticationFilter jwtAuthenticationFilter)
            throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .userDetailsService(userDetailsService)

            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
                .exceptionHandling(exception -> exception
    .authenticationEntryPoint((request, response, authException) -> {
        response.sendError(
            HttpServletResponse.SC_UNAUTHORIZED,
            "No estás autenticado."
        );
    })
)

            .authorizeHttpRequests(auth -> auth

                .requestMatchers(
                    "/",
                    "/index.html",
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/api/auth/login"
                ).permitAll()

                .requestMatchers(HttpMethod.GET, "/api/pagos")
                .hasRole("ADMIN")

                .requestMatchers("/api/**")
                .hasAnyRole("ADMIN", "RECEPCIONISTA")

                .anyRequest().permitAll()
            )

            .addFilterBefore(
                jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration)
            throws Exception {

        return configuration.getAuthenticationManager();
    }
}
