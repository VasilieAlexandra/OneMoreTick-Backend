package com.app.onemoretick.model.validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

public class ValidStartDateValidator implements ConstraintValidator<ValidStartDate, LocalDate> {
    private String message;

    @Override
    public void initialize(final ValidStartDate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(LocalDate startDate, ConstraintValidatorContext constraintValidatorContext) {
        Instant instant= Instant.now();
        LocalDate today = instant.atOffset(ZoneOffset.UTC).toLocalDate();
        if (startDate != null)
            return startDate.isAfter(today);
        return false;
    }
}
