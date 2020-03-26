package Demo17NoUnlock;

import java.util.concurrent.locks.ReentrantLock;

class Runner implements Runnable {
    private int id;
    private int count = 0;

    private ReentrantLock lock = new ReentrantLock();

    public Runner (int id) {
        this.id = id;
    }

    private void increment() {
        for (int i=0;i<10000;i++) {
            count++;
            System.out.println("Count is:"+count);
        }
    }

    @Override
    public void run() {
        lock.lock();
        try {
            increment();
        } finally{
            //lock.unlock();
        }
    }

    public int getCount() {
        return count;
    }
}

public class AppNotUnlock {
    public static void main(String[] args) {
        Runner myRunner  = new Runner(0);
        Thread threads[] = new Thread[2];

        for (int i = 0;i< threads.length;i++) {
            threads[i] = new Thread(myRunner);
        }

        for (int i = 0;i< threads.length;i++) {
            threads[i].start();
        }

        for (int i = 0;i< threads.length;i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Count is: "+ myRunner.getCount());
    }
}
