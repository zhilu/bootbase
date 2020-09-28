package org.common.genetic;


public class SonA extends Father<Integer,String> {

    public void doSomething(){
        System.out.println(this.getAge()+ " "+this.getName());
    }
}
