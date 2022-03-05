
package acme.components.validators;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import acme.framework.datatypes.Money;

public class MoneyValidator implements ConstraintValidator<MoneyConstraint, Money> {

	boolean		allowsNegative;
	boolean		allowsZero;
	String[]	allowedCurrencies;


	@Override
	public void initialize(final MoneyConstraint ann) {
		this.allowsNegative = ann.allowsNegative();
		this.allowsZero = ann.allowsZero();
		this.allowedCurrencies = ann.allowedCurrencies();
	}

	@Override
	public boolean isValid(final Money value, final ConstraintValidatorContext context) {
		if (value == null) return true;
		return this.checkNegativeConstraint(value, context) && this.checkZeroValue(value, context) && this.checkCurrencyValue(value, context);
	}

	private boolean checkNegativeConstraint(final Money value, final ConstraintValidatorContext context) {
		boolean res = true;
		if (!this.allowsNegative && value.getAmount().compareTo(0d) < 0) {
			res = false;
			context.buildConstraintViolationWithTemplate("{acme.components.validators.MoneyValidator.negative.message}").addConstraintViolation();
		}
		return res;
	}

	private boolean checkZeroValue(final Money value, final ConstraintValidatorContext context) {
		boolean res = true;
		if (!this.allowsZero && value.getAmount().compareTo(0d) == 0) {
			res = false;
			context.buildConstraintViolationWithTemplate("{acme.components.validators.MoneyValidator.zero.message}").addConstraintViolation();
		}
		return res;
	}

	private boolean checkCurrencyValue(final Money value, final ConstraintValidatorContext context) {
		boolean res = true;
		final List<String> currencyList = Arrays.asList(this.allowedCurrencies);
		currencyList.replaceAll(String::toUpperCase);
		if (this.allowedCurrencies.length > 0 && currencyList.contains(value.getCurrency().trim().toUpperCase())) {
			res = false;
			context.buildConstraintViolationWithTemplate("{acme.components.validators.MoneyValidator.currencies.message}").addConstraintViolation();
		}
		return res;
	}

}
