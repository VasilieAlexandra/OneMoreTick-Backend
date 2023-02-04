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
    public boolean isValid(LocalDate startDate, ConstraintValidatorContext context) {
        Instant instant= Instant.now();
        LocalDate today = instant.atOffset(ZoneOffset.UTC).toLocalDate();

        boolean isValid = false;

        if(startDate!=null)
            isValid = startDate.isAfter(today) || startDate.equals(today);


        if (!isValid) {
            context.disableDefaultConstraintViolation();
            assert startDate != null;
            context
                    .buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(startDate.toString())
                    .addConstraintViolation();
        }
        return isValid;

    }
}
