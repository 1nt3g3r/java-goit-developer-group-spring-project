package com.goit.devgroup.spring.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/security")
@RestController
public class SecurityController {
    @GetMapping
    public Object get() {
        SecurityContext context = SecurityContextHolder.getContext();

        Authentication auth = context.getAuthentication();

        return auth;
    }
}
