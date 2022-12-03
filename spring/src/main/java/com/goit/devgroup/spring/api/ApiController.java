package com.goit.devgroup.spring.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/api/v1")
@RestController
public class ApiController {
    @GetMapping("/version")
    public Object version() {
        return Map.of(
                "version", "1.0"
        );
    }
}
