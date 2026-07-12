package com.yogendrayadav.codingshuttle.SpringBootWeb.ValidationAnnotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = {RoleValidationLogic.class}
)
public @interface RoleValidation {
    String message() default "Galat role dala hai tune";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
