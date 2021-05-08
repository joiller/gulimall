package com.joiller.gulimall.common.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author jianghuilai
 * @since 2021-04-08 11:49
 **/

public class NonNegativeIntegerValidator implements ConstraintValidator<NonNegativeInteger, Integer> {

    @Override
    public void initialize(NonNegativeInteger constraintAnnotation) {

    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        return integer == null || integer >= 0;
    }
}
