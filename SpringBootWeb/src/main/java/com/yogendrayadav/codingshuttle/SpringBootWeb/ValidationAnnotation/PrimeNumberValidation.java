package com.yogendrayadav.codingshuttle.SpringBootWeb.ValidationAnnotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PrimeNumberValidation implements ConstraintValidator<Prime, Integer>
{
    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        if (integer <= 1) {
            return false;  // 0, 1, negative numbers prime nahi hote
        }

        for (int i = 2; i < integer; i++) {
            if (integer % i == 0) {
                return false;  // Agar koi divisor mil gaya, prime nahi hai
            }
        }

        return true;  // Koi divisor nahi mila, prime hai
    }
}
