package com.works.customnotation;
import com.works.props.CityConstraint;
import org.springframework.validation.annotation.Validated;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CityConstraint.class)
public @interface City {

    String message() default "City Not found";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };


}
