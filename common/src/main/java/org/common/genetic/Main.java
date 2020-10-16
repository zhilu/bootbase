package org.common.genetic;


public class Main {

    public static void main(String[] args) {
        SonA sonA = new SonA();
        sonA.setAge(1);
        sonA.setName("a");
        sonA.doSomething();
        SonB<String,Long> sonB = new SonB();
        sonB.setAge(2L);
        sonB.setName("b");
        sonB.doSomething();
        SonC<Boolean,Boolean,Boolean> sonC = new SonC();
        sonC.setAge(true);
        sonC.setName(false);
        sonC.doSomething();
    }
}
