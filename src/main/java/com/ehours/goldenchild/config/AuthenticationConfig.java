package com.ehours.goldenchild.config;

import com.ehours.goldenchild.authentication.UserAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;

@Configuration
@RequiredArgsConstructor
public class AuthenticationConfig {
    private final UserAuthenticationProvider userAuthenticationProvider;

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(userAuthenticationProvider);
    }
}
