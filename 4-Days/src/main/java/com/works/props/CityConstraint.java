package com.works.props;

import com.works.customnotation.City;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class CityConstraint implements ConstraintValidator<City, String> {

    String[] cityArr = {"İstanbul", "Edirne", "Bursa", "Kocaeli"};

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Arrays.asList(cityArr).contains(value);
    }

}
