package com.validation;

/**
 * Created by user on 16.09.2016.
 */

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PhoneConstraintValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Phone {
    String message() default "Phone number not valid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
