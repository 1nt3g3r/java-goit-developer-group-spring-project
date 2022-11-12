package com.goit.devgroup.spring.cyclic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@Service
public class CommonComponent {
    private final ComponentA a;
    private final ComponentB b;

    @PostConstruct
    public void init() {
        System.out.println("(a == null) = " + (a == null));
        System.out.println("(b == null) = " + (b == null));
    }
}
