package com.unity.user_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF for Postman testing
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/users/register").permitAll() // Allow public access for registration
                .anyRequest().authenticated() // Secure all other endpoints
            )
            .formLogin(form -> form.disable()) // Disable form-based login
            .httpBasic(httpBasic -> httpBasic.disable()); // Disable HTTP Basic Auth if using JWT

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Latest recommended password encoder
    }
}
