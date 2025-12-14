package com.sogeor.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http.authorizeHttpRequests(
                    auth -> auth.requestMatchers("/", "/search", "/product/**", "/css/**", "/js/**", "/images/**",
                                                 "/webjars/**", "/error", "/favicon.ico", "/actuator/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated())
            .oauth2Login(oauth2 -> oauth2.loginPage("/login"))
            .logout(logout -> logout.logoutRequestMatcher(PathPatternRequestMatcher.pathPattern("/logout"))
                                    .logoutSuccessUrl("/")
                                    .deleteCookies("JSESSIONID")
                                    .invalidateHttpSession(true)
                                    .clearAuthentication(true));

        return http.build();
    }

}
