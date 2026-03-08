package com.capgemini.libraryManagementSystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

 @Bean
 public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

  http
  .csrf(csrf->csrf.disable())
  .authorizeHttpRequests(auth->auth
      .requestMatchers("/swagger-ui/**","/v3/api-docs/**","/h2-console/**").permitAll()
      .anyRequest().authenticated()
  )
  .httpBasic(Customizer.withDefaults());

  http.headers(headers->headers.frameOptions(frame->frame.disable()));

  return http.build();
 }

 @Bean
 public UserDetailsService users() {

  UserDetails admin =
    User.withUsername("admin")
    .password("{noop}admin123")
    .roles("ADMIN")
    .build();

  return new InMemoryUserDetailsManager(admin);
 }

}
