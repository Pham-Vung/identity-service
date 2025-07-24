package org.example.identityservice.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateOfBirthValidator implements ConstraintValidator<DateOfBirthConstraint, LocalDate> {
    private int min;

    // method nàydduowuoc khởi tạo mỗi khi constraint được khởi tạo. Sẽ lấy ra được các thông số của annotation đó
    @Override
    public void initialize(DateOfBirthConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        min = constraintAnnotation.min();
    }

    // method này xử lý data truyền vào có đúng hay không
    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        if (localDate == null) {
            return true;
        }

        long years = ChronoUnit.YEARS.between(localDate, LocalDate.now());

        return years >= min;
    }
}
