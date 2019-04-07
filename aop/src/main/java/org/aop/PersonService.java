package org.aop;

import java.time.LocalDate;
import java.time.Period;

import org.common.Person;

public class PersonService {

    public String getFullName(Person person){
    	System.out.println("inner");
        return person.getLastName()+" "+person.getFirstName();
    }
    
    public int getAge(Person person){
        Period p = Period.between(person.getDateOfBirth(), LocalDate.now());
        System.out.println("inner");
        return p.getYears();
    }
    
}
