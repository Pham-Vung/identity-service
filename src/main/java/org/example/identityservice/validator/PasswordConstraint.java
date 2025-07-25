package org.example.identityservice.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = {PasswordValidator.class})
public @interface PasswordConstraint {
    String message() default "Invalid password";

    int min();

    int max();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
