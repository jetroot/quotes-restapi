package com.springboot.redtest.request.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@Constraint(validatedBy = ImageValidator.class)
public @interface Image {

    // Default message
    String message() default "Image cannot be empty";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}