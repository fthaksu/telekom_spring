package com.works.props;

import com.works.customnotation.City;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Student {

    @City
    private String city;

}
