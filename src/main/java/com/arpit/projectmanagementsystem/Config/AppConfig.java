package com.arpit.projectmanagementsystem.Config;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;

@EnableWebSecurity
@Configuration
public class AppConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth.requestMatchers("/api/**").authenticated()
                        .anyRequest().permitAll())
                .addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)
                .cors(cors -> cors.configurationSource(corsConfiguration()));

        return  httpSecurity.build();
    }

    private CorsConfigurationSource corsConfiguration() {

        return  new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

                CorsConfiguration corsConfig = new CorsConfiguration();
                corsConfig.setAllowedOrigins(Arrays.asList(
                        "http://localhost:5173",
                        "http://localhost:3000",
                        "http://localhost:4200"
                ));
                corsConfig.setAllowedMethods(Collections.singletonList("*"));
                corsConfig.setAllowCredentials(true);
                corsConfig.setAllowedHeaders(Collections.singletonList("*"));
                corsConfig.setExposedHeaders(Arrays.asList("Authorization"));
                corsConfig.setMaxAge(3000L);

                return null;
            }
        };
    }

    @Bean
    PasswordEncoder passwordEncoder()
    {
        return  new BCryptPasswordEncoder();
    }
}
