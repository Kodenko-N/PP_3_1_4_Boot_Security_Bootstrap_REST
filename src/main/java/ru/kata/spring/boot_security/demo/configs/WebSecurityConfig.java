package ru.kata.spring.boot_security.demo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private final SuccessUserHandler successUserHandler;

    public WebSecurityConfig(SuccessUserHandler successUserHandler) {
        this.successUserHandler = successUserHandler;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        /*
        http
                .authorizeRequests()
                .requestMatchers("/", "/index").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                // Configure logout functionality
                .logout()
                // Allow everyone to access the logout functionality
                .permitAll();

        http.authorizeHttpRequests(
                        auth -> auth.requestMatchers("/","/login", "/logut", "/index").permitAll()
                                .requestMatchers("/users/**", "/users", "/user").hasAuthority("ADMIN")
                                .requestMatchers("/user").hasAuthority("user")
                                .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .usernameParameter("email")
                        .defaultSuccessUrl("/user", true)
                        .permitAll()
                )
                .rememberMe(rememberMe -> rememberMe.key("AbcdEfghIjkl..."))
                .logout(logout -> logout.logoutUrl("/signout").permitAll());


        return http.build();
       */
        http
                .authorizeHttpRequests()
                .requestMatchers("/", "/index").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().successHandler(successUserHandler)
                .permitAll()
                .and()
                .logout()
                .permitAll();

        // Return the configured SecurityFilterChain
        return http.build();


    }


    // аутентификация inMemory
    @Bean
    public UserDetailsService userDetailsService() {
        List<UserDetails> users = new ArrayList<>();
        users.add(User.withDefaultPasswordEncoder()
                .username("user")
                .password("user")
                .roles("USER")
                .build());
        users.add(User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("ADMIN")
                .build());
/*
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("user")
                        .roles("USER")
                        .build();

 */

        return new InMemoryUserDetailsManager(users);
    }

}