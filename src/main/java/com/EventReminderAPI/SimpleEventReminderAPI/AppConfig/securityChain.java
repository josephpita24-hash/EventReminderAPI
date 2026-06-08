package com.EventReminderAPI.SimpleEventReminderAPI.AppConfig;


// ---- Imports ----
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

// ---- Security Configuration ----

@Configuration // Marks this class as a configuration class for Spring
@EnableWebSecurity // Enables Spring Security's web security support
public class securityChain {

    @Bean // 1. Indicates that this method produces a bean to be managed by Spring
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 2. Disables Cross-Site Request Forgery protection
                .csrf(csrf -> csrf.disable())

                // 3 . Configures authorization rules for HTTP requests
                .authorizeHttpRequests((authz) -> authz
                        // 4 . Allows unauthenticated access to these endpoints
                        .requestMatchers("/login", "/auth/register", "/templates/**").permitAll()
                        // 5. Requires authentication for any other request
                        .anyRequest().authenticated())

                // 6. Enable HTTP Basic for Postman / API clients
                .httpBasic(httpBasic -> httpBasic
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))

                // 7. Handle Exceptinn by returning UNAUTHORIZED instead of redirect to /login
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)));
        // 8. Builds and returns the SecurityFilterChain
        return http.build();
    }
}
