package ms.sysredcolombia.rest.security.configuracion;

import lombok.AllArgsConstructor;
import ms.sysredcolombia.rest.security.filters.JwtAuthFilter;
import ms.sysredcolombia.rest.security.filters.JwtValidationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity)
            throws Exception {
        return httpSecurity.authorizeHttpRequests(
                        auth -> auth.requestMatchers(HttpMethod.GET, "/api/user")
                                .permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/**")
                                .permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/**")
                                .permitAll())
                               /*   .requestMatchers(HttpMethod.GET, "/api/clientes/")
                                .hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/planes")
                                .hasAnyRole("ADMIN", "USER").anyRequest()
                                .authenticated())*/
                .addFilter(new JwtAuthFilter(authenticationManager()))
                .addFilter(new JwtValidationFilter(authenticationManager()))
                .csrf(AbstractHttpConfigurer::disable).sessionManagement(
                        ses -> ses.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS)).build();
    }
}
