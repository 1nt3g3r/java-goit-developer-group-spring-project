package com.goit.devgroup.spring;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Scope("prototype")
@Service
public class ScopeTestComponent {
    {
        System.out.println("New ScopeTestComponent created!");
    }
}
