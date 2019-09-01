package shi.mvc.validation.jsr303;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class Jsr303JavaxValidatorDemo {

	public static void main(String... args) {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		ValidationService beanValidationService = ctx.getBean(ValidationService.class);
		Person person = ctx.getBean(Person.class);
		person.setAddress("1111111");
		Set<ConstraintViolation<Person>> violations = beanValidationService.validate(person);
		violations.forEach(x->System.out.println("Validation error for property: " + x.getPropertyPath() + " with value: "
					+ x.getInvalidValue() + " with error message: " + x.getMessage()));
		
		
		ctx.close();
	}
}