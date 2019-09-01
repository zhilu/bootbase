package shi.mvc.validation.jsr303;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("validationService")
public class ValidationService {
	
	@Autowired
	private Validator validator;

	public Set<ConstraintViolation<Person>> validate(Person person) {
		return validator.validate(person);
	}
}