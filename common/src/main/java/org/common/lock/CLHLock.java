package org.common.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author sgh2759
 * @description: CLH lock
 * @date 2021-02-19
 */
public class CLHLock{

    private AtomicReference<Node> tail = new AtomicReference<>();
    private ThreadLocal<Node> threadLocalNode = new ThreadLocal<>();

    public void lock(){
        Node node = threadLocalNode.get();
        if(node == null){
            node = new Node();
            node.setLocked(true);
            threadLocalNode.set(node);
        }
        Node preNode = tail.getAndSet(node);
        if(preNode == null){
            return;
        }

        while (preNode.locked){
            // wait
        }
    }


    public void unlock(){
        Node node = threadLocalNode.get();
        if(node == null || node.locked == false){
            return;
        }
        if(tail.compareAndSet(node,null)){

        }else {
            node.setLocked(false);
        }
    }


    static final class Node{
        private volatile boolean locked = true;

        public boolean isLocked() {
            return locked;
        }

        public void setLocked(boolean locked) {
            this.locked = locked;
        }
    }


    public static void main(String[] args) {
        CLHLock lock = new CLHLock();
        new Thread(()-> {
            lock.lock();
            try {
                System.out.println("thread 1 start");
                Thread.sleep(3000);
                System.out.println("thread 1 end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();

        new Thread(()-> {
            lock.lock();
            try {
                System.out.println("thread 2 start");
                Thread.sleep(3000);
                System.out.println("thread 2 end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();
    }

}
