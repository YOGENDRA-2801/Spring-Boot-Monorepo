package com.yogendrayadav.codingshuttle.SpringBootWeb.ValidationAnnotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        boolean uppercase = s.chars().anyMatch(Character::isUpperCase) ;
        boolean lowercase = s.chars().anyMatch(Character::isLowerCase) ;
        boolean specialCharacter = s.chars().anyMatch(value -> !Character.isLetterOrDigit(value));
        boolean minLength = s.length() > 10 ;

        return uppercase && lowercase && specialCharacter && minLength;
    }
}
