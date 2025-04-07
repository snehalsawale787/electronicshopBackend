package com.commerce.electronicshop.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ImageNameValidator implements ConstraintValidator<ImageNameValid,String>
{

    public boolean isValid(String value, ConstraintValidatorContext context)
    {
        //logic
        return false;
    }
}
