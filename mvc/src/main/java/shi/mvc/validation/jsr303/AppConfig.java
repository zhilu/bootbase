package shi.mvc.validation.jsr303;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@Configuration
@ComponentScan(basePackages = "shi.mvc.validation.jsr303")
public class AppConfig {
	@Bean
	LocalValidatorFactoryBean validator() {
		return new LocalValidatorFactoryBean();
	}
	
	@Bean
	MethodValidationPostProcessor postProcessor() {
		MethodValidationPostProcessor bean = new MethodValidationPostProcessor();
		bean.setValidator(validator());
		return bean;
	}
}
