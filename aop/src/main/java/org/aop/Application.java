package org.aop;

import org.common.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class Application {


	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Application.class, args);
		
		String[] names = context.getBeanDefinitionNames();
		log.info("----");
		
		for (String name : names) {
			log.info(name);
		}
		
		log.info("----");
		
		Person person = (Person) context.getBean("person");
		PersonService personService = (PersonService) context.getBean("personService");

		log.info("Name is:" + personService.getFullName(person));
		log.info("Age is:" + personService.getAge(person));
	}

}
