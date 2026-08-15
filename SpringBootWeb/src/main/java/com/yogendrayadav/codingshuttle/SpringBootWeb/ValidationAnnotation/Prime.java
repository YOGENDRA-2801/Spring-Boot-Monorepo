package com.yogendrayadav.codingshuttle.SpringBootWeb.ValidationAnnotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = {PrimeNumberValidation.class}
)
public @interface Prime {
    String message() default "Must be a prime number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
