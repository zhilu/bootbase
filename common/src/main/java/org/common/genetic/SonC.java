package org.common.genetic;

public class SonC<A,B,C> extends Father<A,B> {

    public void doSomething(){
        System.out.println(this.getAge()+ " "+this.getName());
    }
}
