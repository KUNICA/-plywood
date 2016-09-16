package com.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by user on 16.09.2016.
 */
@Documented
@Constraint(validatedBy = UserNameConstraintValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UserName {
    String message() default "This user already exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
