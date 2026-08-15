package com.yogendrayadav.codingshuttle.SpringBootWeb.ValidationAnnotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class RoleValidationLogic implements ConstraintValidator<RoleValidation, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) return false ;
        List allRoles = List.of("ADMIN", "USER");
        return allRoles.contains(s);
    }
}
