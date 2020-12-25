package org.common.annotation;

/**
 * javac -processor com.example.lombok.SimpleGetterProcessor Person.java
 */
@SimpleGetter
public class Person {
    private String name;
}
