package com.yogendrayadav.codingshuttle.Introduction;

//import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

//@Component
public class PaymentService {
    public void pay() {
        System.out.println("Paying...");
    }

    @PostConstruct
    public void afterInit() {
        System.out.println("Initialization of this bean is completed");
    }

    @PreDestroy
    public void beforeDestroy() {
        System.out.println("This bean is about to get destroyed");
    }
}
