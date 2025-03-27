package ru.kata.spring.boot_security.demo.configs;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class SuccessUserHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains("ADMIN")) {
            System.out.println("Admin logged in");
            httpServletResponse.sendRedirect("/admin");
        }
        else if (roles.contains("USER")) {
            System.out.println("User logged in");
            httpServletResponse.sendRedirect("/user");
        }
        else {
            System.out.println("Access denied for " + authentication.getName() + ", role is "+ roles);
            System.out.println("Roles contain ADMIN? " + roles.contains("ADMIN"));
            System.out.println("Roles contain USER? " + roles.contains("USER"));
            httpServletResponse.sendRedirect("/403");
        }
    }
}