package shi.mvc.validation.spring;

import java.util.List;

import org.common.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


public class SpringValidatorDemo {

	public static void main(String... args) {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		Person singer = new Person();
		singer.setFirstName(null);
		singer.setLastName("Mayer");
		Validator singerValidator = ctx.getBean("personValidator", Validator.class);
		BeanPropertyBindingResult result = new BeanPropertyBindingResult(singer, "John");
		ValidationUtils.invokeValidator(singerValidator, singer, result);
		List<ObjectError> errors = result.getAllErrors();
		System.out.println("No of validation errors: " + errors.size());
		errors.forEach(e -> System.out.println(e.getCode()));
		ctx.close();
	}
}