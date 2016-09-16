package com.validation;

import com.dataweb.Registration;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by user on 15.09.2016.
 */
public class RegistrationValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Registration.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Registration registration = (Registration) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fullName", "fullName.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "lastName.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "adress", "adress.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "zip", "zip.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "phone.required");
    }
}
