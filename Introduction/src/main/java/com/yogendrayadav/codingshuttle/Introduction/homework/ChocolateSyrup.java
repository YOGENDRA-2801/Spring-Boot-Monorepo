package com.yogendrayadav.codingshuttle.Introduction.homework;

import org.springframework.stereotype.Component;

@Component("chocolateSyrup")
public class ChocolateSyrup implements Syrup {
    @Override
    public void getSyrupType() {
        System.out.println("Adding Chocolate Syrup");
    }
}
