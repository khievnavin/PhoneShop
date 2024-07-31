package com.product.phoneshop.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable) // Disable CSRF protection
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/index", "/home", "css/**", "js/**", "/error").permitAll()
                        .requestMatchers("/models").hasRole("SALE")
                        .requestMatchers(HttpMethod.POST, "/brands").hasAuthority(PermissionEnum.BRAND_WRITE.getDescription())
                        .requestMatchers(HttpMethod.GET , "/brands").hasAuthority(PermissionEnum.BRAND_READ.getDescription())
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        //UserDetails
        //User navin = new User("navin", passwordEncoder.encode("navin123"), Collections.emptyList());
        UserDetails navin = User.builder()
                .username("navin")
                .password(passwordEncoder.encode("navin"))
                .authorities(RoleEnum.ADMIN.getAuthorities())
                //.roles("ADMIN") //ROLE_ADMIN
                .build();

        UserDetails savin = User.builder()
                .username("savin")
                .password(passwordEncoder.encode("savin"))
                .authorities(RoleEnum.SALE.getAuthorities())
                //.roles("SALE") //ROLE_SALE
                .build();

        return new InMemoryUserDetailsManager(navin,savin);
    }
}