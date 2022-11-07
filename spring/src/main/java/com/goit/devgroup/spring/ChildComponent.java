package com.goit.devgroup.spring;

import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;

@Service
public class ChildComponent {
    {
        System.out.println("Init ChildComponent");
    }

    public void printHello() {
        System.out.println("Hello, I'm child component");
    }

    @PreDestroy
    public void onDestroy() {
        System.out.println("ChildComponent shutdown");
    }
}
