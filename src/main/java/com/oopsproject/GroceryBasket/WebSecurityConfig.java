package com.oopsproject.GroceryBasket;

import com.oopsproject.GroceryBasket.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .antMatchers("/", "/home","/login","/register","/registerPage","/about","/error").permitAll()
                        .antMatchers("/customer/**").permitAll()
                        .antMatchers("/user/**").hasAnyRole(new String[]{"ADMIN", "MANAGER", "USER"})
                        .antMatchers("/admin/**").hasAnyRole(new String[]{"ADMIN"})
                        .antMatchers("/manager/**").hasAnyRole(new String[]{"ADMIN", "MANAGER"})
                        .antMatchers("/reports/admin/**").hasAnyRole(new String[]{"ADMIN"})
                        .antMatchers("/reports/user/**").hasAnyRole(new String[]{"ADMIN", "MANAGER", "USER"})
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll())
                .csrf().disable();

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }
}