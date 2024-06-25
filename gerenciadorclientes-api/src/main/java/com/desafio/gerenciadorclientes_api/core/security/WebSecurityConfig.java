package com.desafio.gerenciadorclientes_api.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user").password("{noop}123qwe123").roles("USER").build());
        manager.createUser(User.withUsername("admin").password("{noop}123qwe!@#").roles("ADMIN").build());
        return manager;
    }

    @SuppressWarnings("removal")
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .headers(headers -> headers.frameOptions().disable())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(HttpMethod.POST, "/clientes/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/clientes/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/clientes/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/usuarios/**").hasRole("ADMIN")
                        .requestMatchers("/endereco/**").hasRole("ADMIN")
                        .anyRequest().permitAll())
                .httpBasic(withDefaults())
                .exceptionHandling(handling -> handling
                        .authenticationEntryPoint(authenticationEntryPoint())
                        .accessDeniedHandler(accessDeniedHandler()));
            
        return http.build();
    }

    @Bean
    AuthenticationEntryPoint authenticationEntryPoint() {
        return new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED);
    }

    @Bean
    AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Você não tem permissão para acessar este recurso.");
        };
    }

}
