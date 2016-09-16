package com.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by user on 16.09.2016.
 */
public class ZipCodeConstraintValidator implements ConstraintValidator<ZipCode, String> {
    @Override
    public void initialize(ZipCode zipCode) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isValid(String phoneField, ConstraintValidatorContext cxt) {
        if(phoneField == null) {
            return false;
        }
        return phoneField.matches("^\\d{5}(?:[-\\s]\\d{4})?$");
    }
}
