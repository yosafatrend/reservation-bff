package com.bff.reservation.thirdparty.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class TptSecurityConfig {
    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
    //     http.csrf(AbstractHttpConfigurer::disable)
    //         .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
    //         authorizationManagerRequestMatcherRegistry.requestMatchers("/api/thirdparty/**").authenticated())
    //         .httpBasic(Customizer.withDefaults())
    //         .sessionManagement(httpSecuritySessionManagemenConfigurer ->
    //         httpSecuritySessionManagemenConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    //         .addFilterBefore(new TptAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    //     return http.build();
    // }
}
