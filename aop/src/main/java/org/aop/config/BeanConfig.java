package org.aop.config;

import java.time.LocalDate;
import java.time.Month;

import org.aop.PersonService;
import org.common.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
	
	@Bean
	public Person person() {
		return new Person("John", "Smith", LocalDate.of(1980, Month.JANUARY, 12));
	}

	@Bean
	public PersonService personService() {
		return new PersonService();
	}

}
