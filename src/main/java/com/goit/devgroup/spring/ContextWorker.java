package com.goit.devgroup.spring;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@Service
public class ContextWorker {
    private final ApplicationContext context;

    @PostConstruct
    public void init() {
        System.out.println("Call 1");
        ScopeTestComponent test1 = context.getBean(ScopeTestComponent.class);

        System.out.println("Call 2");
        ScopeTestComponent test2 = context.getBean(ScopeTestComponent.class);
    }
}
