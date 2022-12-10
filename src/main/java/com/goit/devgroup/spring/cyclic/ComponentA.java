package com.goit.devgroup.spring.cyclic;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@Service
public class ComponentA {
    private final ApplicationContext context;

    private ComponentB b;

    @PostConstruct
    public void init() {
//        b = context.getBean(ComponentB.class);

        System.out.println("(b == null) = " + (b == null));
    }

    private ComponentB getB() {
        return context.getBean(ComponentB.class);
    }
}
