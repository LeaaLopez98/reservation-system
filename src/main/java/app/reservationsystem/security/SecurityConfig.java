package app.reservationsystem.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final AuthenticationProvider provider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.
                csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req
                                .requestMatchers(HttpMethod.POST, "/clubs/**").hasRole("OWNER")
                                .requestMatchers(HttpMethod.GET, "/clubs/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/clubs/me").hasRole("OWNER")
                                .requestMatchers(HttpMethod.DELETE, "/clubs/**").hasRole("OWNER")
                                .requestMatchers(HttpMethod.PUT, "/clubs/**").hasRole("OWNER")

                                .requestMatchers(HttpMethod.GET, "/clubs/{id-club}/reservations/**").permitAll()

                                .requestMatchers(HttpMethod.GET, "/fields/**").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/fields/**").hasRole("OWNER")
                                .requestMatchers(HttpMethod.PUT, "/fields/**").hasRole("OWNER")

                                .requestMatchers(HttpMethod.POST, "/reservations/**").hasRole("PLAYER")
                                .requestMatchers(HttpMethod.DELETE, "/reservations/**").authenticated()
                                .requestMatchers(HttpMethod.GET, "/reservations/**").authenticated()


                                .requestMatchers("/users/**").permitAll()
                                .requestMatchers("/swagger-ui/**").permitAll()
                                .requestMatchers("/v3/api-docs/**").permitAll()
                                .anyRequest().authenticated()
                )
                .authenticationProvider(provider)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
