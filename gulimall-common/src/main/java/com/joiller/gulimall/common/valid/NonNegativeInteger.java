package com.joiller.gulimall.common.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
//@Repeatable(ListValue.List.class)
@Documented
@Constraint(
        validatedBy = {NonNegativeIntegerValidator.class}
)
public @interface NonNegativeInteger {
    String message() default "{com.joiller.gulimall.common.valid.NonNegativeInteger.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
