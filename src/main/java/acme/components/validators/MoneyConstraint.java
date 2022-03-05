package acme.components.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = MoneyValidator.class)
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface MoneyConstraint {

	String message() default "{acme.components.validators.MoneyValidator.default}";
    Class<?>[] groups() default {};
    boolean allowsNegative() default true;
    boolean allowsZero() default true;
    String[] allowedCurrencies() default {};
    Class<? extends Payload>[] payload() default {};
}
