package com.gabrielpenteado.apijavaspringbootdocker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationConverter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Filters
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // .securityMatcher("/users/**")
                .authorizeHttpRequests(
                        authorizeConfig -> {
                            authorizeConfig.requestMatchers("/users/all").permitAll();
                            // authorizeConfig.requestMatchers("users/user/email").permitAll();
                            authorizeConfig.anyRequest().authenticated();
                        })
                // .formLogin(Customizer.withDefaults())
                .oauth2Login(Customizer.withDefaults()) // for cookies
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults())) // for jwt
                // .sessionManagement(session ->
                // session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

}
