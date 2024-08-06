package com.product.phoneshop.config.security;

import com.product.phoneshop.config.security.jwt.JwtLoginFilter;
import com.product.phoneshop.config.security.jwt.TokenVerifyFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)

public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final AuthenticationConfiguration authenticationConfiguration;

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable) // Disable CSRF protection
                .addFilter(new JwtLoginFilter(authenticationManager(authenticationConfiguration)))
                .addFilterAfter(new TokenVerifyFilter(), JwtLoginFilter.class)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/index", "/home", "css/**", "js/**", "/error").permitAll()
                        //.requestMatchers("/models").hasRole("SALE")
                        //.requestMatchers(HttpMethod.POST, "/brands").hasAuthority(PermissionEnum.BRAND_WRITE.getDescription())
                        //.requestMatchers(HttpMethod.GET, "/brands").hasAuthority(PermissionEnum.BRAND_READ.getDescription())
                        .anyRequest()
                        .authenticated()
                );
        return http.build();
    }

//    @Autowired
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(getAuthenticationProvider());
//    }


    @Bean
    public AuthenticationProvider getAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /*
    @Bean
    public UserDetailsService userDetailsService() {

        return null;
    }

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

      */
}