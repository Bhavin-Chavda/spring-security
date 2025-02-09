package com.example.Security08022025.config;

import com.example.Security08022025.service.UserInfoUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {


    //Authentication
    @Bean
    public UserDetailsService userDetailsService()
    {
//        UserDetails admin = User.withUsername("bhavin")
//                .password(passwordEncoder().encode("pwd1"))
//                        .roles("ADMIN").build();
//        UserDetails user = User.withUsername("kripal")
//                .password(passwordEncoder().encode("pwd2"))
//                .roles("USER").build();
//
//        return  new InMemoryUserDetailsManager(admin,user);

        return new UserInfoUserDetailsService();
    }


    //Authorization Define
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable()) // Disable CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/products/welcome" , "/users/new").permitAll()
                        .requestMatchers("/products/**").authenticated()
                )
                .httpBasic(Customizer.withDefaults()) // ✅ Enable Basic Authentication
                .formLogin(Customizer.withDefaults()) // ✅ Enable Form Login
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
}
