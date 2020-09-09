package org.apache.spi;

public class OptimusPrime implements Robot {

    @Override
    public void sayHello() {
        System.out.println("Hello, I am Optimus Prime.");
    }

    public static class Bumblebee implements Robot {

        @Override
        public void sayHello() {
            System.out.println("Hello, I am org.apache.spi.OptimusPrime.Bumblebee.");
        }
    }
}