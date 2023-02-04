package com.app.onemoretick.model.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

import java.time.LocalDate;

public class ValidEndDateValidator implements ConstraintValidator<ValidEndDate, Object> {
    private String startDate;
    private String endDate;
    private String message;
    @Override
    public void initialize(final ValidEndDate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.endDate = constraintAnnotation.e();
        this.startDate = constraintAnnotation.s();
        this.message = constraintAnnotation.message();

    }

    @Override
    public boolean isValid(final Object value, ConstraintValidatorContext context) {
        Object startDateValue = new BeanWrapperImpl(value).getPropertyValue(startDate);
        Object endDateValue = new BeanWrapperImpl(value).getPropertyValue(endDate);
        LocalDate start = (LocalDate)startDateValue;
        LocalDate end = (LocalDate)endDateValue;
        boolean isValid = false;

        if(end!=null)
            isValid = end.isAfter(start) || end.equals(start);


        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context
                    .buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(endDate)
                    .addConstraintViolation();
        }
        return isValid;
    }
}
