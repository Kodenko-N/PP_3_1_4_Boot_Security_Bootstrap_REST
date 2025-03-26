package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.kata.spring.boot_security.demo.service.CustomUserDetailsService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final SuccessUserHandler successUserHandler;
    private CustomUserDetailsService userDetailsService;
    private UserService userService;

    public WebSecurityConfig(SuccessUserHandler successUserHandler, UserService userService, CustomUserDetailsService userDetailsService) {
        this.successUserHandler = successUserHandler;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService);
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                        auth -> auth.requestMatchers("/","/index","/403").permitAll()
                                .requestMatchers("/user").hasAnyAuthority("USER", "ADMIN")
                                .requestMatchers("/**").hasAuthority("ADMIN")
                                .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .permitAll().successHandler(successUserHandler)
                )
                .logout(logout -> logout.logoutSuccessUrl("/").permitAll()
                )
                .exceptionHandling(exceptions -> exceptions
                        .accessDeniedPage("/403") // Страница для ошибки доступа
                );


        return http.build();
    }

    //аутентификация из БД


    @Bean
    public UserDetailsService userDetailsService(){
        return new CustomUserDetailsService();
    }

    /*
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
        return new InMemoryUserDetailsManager(users);
    }

     */

}