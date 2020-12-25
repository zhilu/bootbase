package org.common.thread;

public class OneByOneJoin {

    public static char[] chars= {'1','a','2','b','3','c'};

    public static void main(String[] args) {
        Thread thread = Thread.currentThread();
        for (int i = 0; i < chars.length; i++) {
            thread = new Thread(new JoinWorker(thread,chars[i]));
            thread.start();
        }

        //--------------------------
    }

}


class JoinWorker implements Runnable{

    private Thread previous;
    private char c;

    public JoinWorker(Thread previous,char c){
        this.previous =previous;
        this.c =c;
    }

    @Override
    public void run() {
        try {
            previous.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(c);
    }
}
