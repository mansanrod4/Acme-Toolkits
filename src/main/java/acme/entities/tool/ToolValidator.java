package acme.entities.tool;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ToolValidator implements Validator{
	
	private static final String NEGATIVE_AMOUNT = "must be positive or zero";

	@Override
	public boolean supports(final Class<?> clazz) {
		return Tool.class.equals(clazz);
	}

	@Override
	public void validate(final Object target, final Errors errors) {
		final Tool tool = (Tool) target;
		//Retail Price validation
		if (tool.retailPrice.getAmount() < 0) {
			errors.rejectValue("retail_price", ToolValidator.NEGATIVE_AMOUNT, "retail price" + ToolValidator.NEGATIVE_AMOUNT);
		}
	}

}
