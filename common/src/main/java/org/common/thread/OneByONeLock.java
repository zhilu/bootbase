package org.common.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OneByONeLock {

    public static char[] chars= {'1','a','2','b','3','c'};

    public static int current = 0;

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        for (int i = 0; i < chars.length; i++) {
            Thread thread = new Thread(new LockWorker(lock,condition));
            thread.start();
        }
    }

}


class LockWorker implements Runnable{

    public static volatile boolean even = true;
    private Lock lock;
    private Condition condition;

    public LockWorker(Lock lock,Condition condition){
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        lock.lock();
        try{
            boolean ok = even && OneByONeLock.current % 2 == 0;
            ok = ok || (!even && OneByONeLock.current % 2 != 0);
            if(ok){
                System.out.println(OneByONeLock.chars[OneByONeLock.current]);
                even = !even;
                OneByONeLock.current++;
                condition.signalAll();
            }else {
                condition.await();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }



    }
}

