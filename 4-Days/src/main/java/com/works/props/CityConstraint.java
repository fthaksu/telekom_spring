package com.works.props;

import com.works.customnotation.City;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CityConstraint implements ConstraintValidator<City, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return false;
    }

}
