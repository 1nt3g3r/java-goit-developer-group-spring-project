package com.goit.devgroup.spring;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@Component
public class AParentComponent {
    private final ChildComponent childComponent;

    {
        System.out.println("Init ParentComponent");
    }

    @PostConstruct
    public void init() {
        childComponent.printHello();
    }

    @PostConstruct
    public void init2() {
        System.out.println("Init 2");
    }
}
