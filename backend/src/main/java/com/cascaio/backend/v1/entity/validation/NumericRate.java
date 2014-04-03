package com.cascaio.backend.v1.entity.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Documented
@Constraint(validatedBy = NumericRateValidator.class)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface NumericRate {
    String message() default "{com.cascaio.validation.NumericRate.invalidRate}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
