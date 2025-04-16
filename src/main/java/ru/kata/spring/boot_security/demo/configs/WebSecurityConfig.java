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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UserServiceImp;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final SuccessUserHandler successUserHandler;
    private final UserService userService;

    public WebSecurityConfig(SuccessUserHandler successUserHandler, UserService userService) {
        this.successUserHandler = successUserHandler;
        this.userService = userService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(
//                        auth -> auth.requestMatchers("/", "/index", "/403", "/login", "/logout","/css/**", "/error", "/api/currentuser").permitAll()
//                                .requestMatchers("/user").hasAnyAuthority("USER", "ADMIN")
//                                .requestMatchers("/**").hasAuthority("ADMIN")
//                                .anyRequest().authenticated()
//                )
//                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//                .formLogin(form -> form
//                        .loginPage("/login")
//                        .loginProcessingUrl("/login")
//                        .successHandler(successUserHandler)
//                )
//                .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/").permitAll()
//                )
//                .exceptionHandling(exceptions -> exceptions
//                        .accessDeniedPage("/403") // Страница для ошибки доступа
//                );

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        auth -> auth.requestMatchers("/", "/index", "/403", "/login", "/logout","/css/**", "/error", "/api/**").permitAll()
                                .requestMatchers("/user").hasAnyAuthority("USER", "ADMIN")
                                .requestMatchers("/**").hasAuthority("ADMIN")
                                .anyRequest().authenticated()
                )
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .successHandler(successUserHandler)
                )
                .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/").permitAll()
                )
                .exceptionHandling(exceptions -> exceptions
                        .accessDeniedPage("/403") // Страница для ошибки доступа
                );


        return http.build();
    }

    //DB authentication
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserServiceImp();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}