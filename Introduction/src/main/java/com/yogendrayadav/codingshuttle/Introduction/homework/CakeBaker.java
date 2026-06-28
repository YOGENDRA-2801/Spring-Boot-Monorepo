package com.yogendrayadav.codingshuttle.Introduction.homework;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CakeBaker {

    private final Frosting frosting ;
    private final Syrup syrup ;

    public CakeBaker(@Qualifier("chocolateFrosting") Frosting frosting, @Qualifier("strawberrySyrup") Syrup syrup) {
        this.frosting = frosting;
        this.syrup = syrup;
    }

    public void bakeCake() {
        frosting.getFrostingType();
        syrup.getSyrupType();
        System.out.println("Mixing .... and Done");
    }
}
