package com.yogendrayadav.codingshuttle.Introduction.homework;

import org.springframework.stereotype.Component;

@Component("strawberryFrosting")
public class StrawberryFrosting implements Frosting{
    @Override
    public void getFrostingType() {
        System.out.println("Adding Strawberry Frosting");
    }
}
