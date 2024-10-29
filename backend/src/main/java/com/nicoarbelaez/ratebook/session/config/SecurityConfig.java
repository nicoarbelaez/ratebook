package com.nicoarbelaez.ratebook.session.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.nicoarbelaez.ratebook.session.jwt.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter authenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth
                            .requestMatchers("/api/auth/me").hasRole("USER")
                            .requestMatchers("/api/users").hasRole("ADMIN")
                            .anyRequest().permitAll();
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    // @Bean
    // public AuthenticationManager
    // authenticationManager(AuthenticationConfiguration config) throws Exception {
    // return config.getAuthenticationManager();
    // }

    // @Bean
    // public AuthenticationProvider authenticationProvider() {
    // DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    // provider.setUserDetailsService(userDetailsService());
    // provider.setPasswordEncoder(passwordEncoder());
    // return provider;
    // }

    // @Bean
    // public PasswordEncoder passwordEncoder() {
    // return NoOpPasswordEncoder.getInstance();
    // // return new BCryptPasswordEncoder();
    // }

    // @Bean
    // public UserDetailsService userDetailsService() {
    // return new UserDetailsService() {
    // @Override
    // public UserDetails loadUserByUsername(String email) {
    // return authRepository.findByEmail(email)
    // .map(Auth::getUser).orElseThrow(() -> new RuntimeException("User not
    // found"));
    // }
    // };
    // }
}
