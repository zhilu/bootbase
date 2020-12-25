package org.common.thread;

import lombok.SneakyThrows;

public class OneByOneWait {

    public static char[] nums= {'1','2','3'};
    public static char[] chars= {'a','b','c'};

    public static volatile boolean flag = true;


    public static void main(String[] args) {
        Object lock = new Object();
        Thread notify = new Thread(new NotifyWorker(lock,nums));
        Thread wait = new Thread(new WaitWorker(lock,chars));
        notify.start();
        wait.start();

    }

}

class NotifyWorker  implements Runnable{

    private Object lock;
    private char[] chars = null;
    private int current = 0;

    NotifyWorker(Object lock,char[] chars){
        this.lock =lock;
        this.chars =chars;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (current < chars.length){
            synchronized (lock){
                while (!OneByOneWait.flag){
                    lock.wait();
                }
                System.out.println(chars[current++]);
                OneByOneWait.flag =false;
                lock.notifyAll();
            }
        }
    }
}

class WaitWorker  implements Runnable{

    private Object lock;
    private char[] chars = null;
    private int current = 0;

    WaitWorker(Object lock,char[] chars){
        this.lock =lock;
        this.chars =chars;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (current < chars.length){
            synchronized (lock){
                while (OneByOneWait.flag){
                    lock.wait();
                }
                System.out.println(chars[current++]);
                OneByOneWait.flag = true;
                lock.notifyAll();
            }
        }
    }
}