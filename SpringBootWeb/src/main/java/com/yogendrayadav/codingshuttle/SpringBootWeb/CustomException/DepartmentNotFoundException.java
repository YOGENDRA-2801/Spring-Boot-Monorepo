package com.yogendrayadav.codingshuttle.SpringBootWeb.CustomException;

public class DepartmentNotFoundException extends RuntimeException
{
    public DepartmentNotFoundException(String message) {
        super(message);
    }
}
