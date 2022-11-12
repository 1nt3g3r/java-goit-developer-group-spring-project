package com.goit.devgroup.spring.cyclic;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@Service
public class ComponentB {
    private final ApplicationContext context;

    private ComponentA a;

    @PostConstruct
    public void init() {
//        a = context.getBean(ComponentA.class);

        System.out.println("(a == null) = " + (a == null));
    }
}
