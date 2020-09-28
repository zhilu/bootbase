package org.common.genetic;


public class SonB<N,A> extends Father<Long,N> {

    public void doSomething(){
        System.out.println(this.getAge()+ " "+this.getName());
    }
}
