package org.common.genetic;


public class Father<T,N> {
    private T age;
    private N name;

    public T getAge() {
        return age;
    }

    public void setAge(T age) {
        this.age = age;
    }

    public N getName() {
        return name;
    }

    public void setName(N name) {
        this.name = name;
    }
}
