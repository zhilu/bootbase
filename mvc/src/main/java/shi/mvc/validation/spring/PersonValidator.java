package shi.mvc.validation.spring;

import org.common.Person;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("personValidator")
public class PersonValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return Person.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors e) {
		ValidationUtils.rejectIfEmpty(e, "firstName", "firstName.empty");
	}
}