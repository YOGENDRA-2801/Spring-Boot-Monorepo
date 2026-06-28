package com.yogendrayadav.codingshuttle.Introduction.homework;

import org.springframework.stereotype.Component;

@Component("chocolateFrosting")
public class ChocolateFrosting implements Frosting{
    @Override
    public void getFrostingType() {
        System.out.println("Adding Chocolate Frosting");
    }
}
