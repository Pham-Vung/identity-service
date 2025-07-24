package org.example.identityservice.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) //  chú thích mức độ tồn tại của một annotation nào đó
@Target(ElementType.FIELD) // chú thích phạm vi sử dụng của một Annotation
@Constraint(validatedBy = {DateOfBirthValidator.class})// class sẽ validate cho annotation này
public @interface DateOfBirthConstraint {
    String message() default "Invalid date of birth";

    int min();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
