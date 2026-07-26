package com.yogendrayadav.codingshuttle.SpringBootWeb.CustomException;


public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
