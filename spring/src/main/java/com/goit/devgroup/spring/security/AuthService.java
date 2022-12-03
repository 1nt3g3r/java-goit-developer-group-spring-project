package com.goit.devgroup.spring.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {
    public SecurityResponse getSecurityResponse() {
        Set<String> roles = getUserRoles();

        if (roles.contains("ADMIN")) {
            return new SecurityResponse("You're admin! Cool!");
        }

        if (roles.contains("USER")) {
            return new SecurityResponse("You're user! That's okay!");
        }

        if (roles.contains("ANONYMOUS")) {
            return new SecurityResponse("You're anonymous.");
        }

        return new SecurityResponse("Who are you? who knows.");
    }

    public boolean isAdmin() {
        return getUserRoles().contains("ADMIN");
    }

    private Set<String> getUserRoles() {
        System.out.println("SecurityContextHolder.getContextHolderStrategy().getClass() = " + SecurityContextHolder.getContextHolderStrategy().getClass());

        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .map(it -> it.replace("ROLE_", ""))
                .collect(Collectors.toSet());
    }
}
