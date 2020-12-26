package org.common.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OneByOneLockTwoCondition {


    public static char[] chars= {'1','a','2','b','3','c'};

    public static int current = 0;

    public static void main(String[] args) {
        Lock newLock = new ReentrantLock();
        Condition even = newLock.newCondition();
        Condition odd = newLock.newCondition();

        for (int i = 0; i < chars.length/2; i++) {
            Thread thread = new Thread(new EvenWorker(newLock,even,odd));
            thread.start();
            thread = new Thread(new OddWorker(newLock,even,odd));
            thread.start();

        }
    }
}


class EvenWorker implements Runnable{

    private Lock lock;
    private Condition even;
    private Condition odd;

    public EvenWorker(Lock lock,Condition even,Condition odd){
        this.lock = lock;
        this.even = even;
        this.odd = odd;
    }

    @Override
    public void run() {
        lock.lock();
        try{
            if(OneByOneLockTwoCondition.current % 2 == 0){
                System.out.println(OneByOneLockTwoCondition.chars[OneByOneLockTwoCondition.current]);
                OneByOneLockTwoCondition.current++;
                odd.signalAll();
            }else {
                even.await();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }



    }
}

class OddWorker implements Runnable{

    private Lock lock;
    private Condition even;
    private Condition odd;

    public OddWorker(Lock lock,Condition even,Condition odd){
        this.lock = lock;
        this.even = even;
        this.odd = odd;
    }

    @Override
    public void run() {
        lock.lock();
        try{
            if(OneByOneLockTwoCondition.current % 2 != 0){
                System.out.println(OneByOneLockTwoCondition.chars[OneByOneLockTwoCondition.current]);
                OneByOneLockTwoCondition.current++;
                even.signalAll();
            }else {
                odd.await();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

