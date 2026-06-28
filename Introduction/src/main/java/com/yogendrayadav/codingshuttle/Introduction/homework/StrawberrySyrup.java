package com.yogendrayadav.codingshuttle.Introduction.homework;

import org.springframework.stereotype.Component;

@Component("strawberrySyrup")
public class StrawberrySyrup implements Syrup{
    @Override
    public void getSyrupType() {
        System.out.println("Adding Strawberry Syrup");
    }
}
