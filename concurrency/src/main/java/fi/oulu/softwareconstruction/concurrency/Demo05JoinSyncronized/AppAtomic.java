package fi.oulu.softwareconstruction.concurrency.Demo05JoinSyncronized;

import java.util.concurrent.atomic.AtomicInteger;

class RunnerAtomic implements Runnable {
    private int id;
    private AtomicInteger count;
    public RunnerAtomic (int id) {
        this.id = id;
        count = new AtomicInteger();
        count.set(0);
    }

    private void increment() {
        for (int i=0;i<10000;i++) {
            count.incrementAndGet();
        }
    }

    @Override
    public void run() {
        increment();
    }

    public int getCount() {
        return count.get();
    }
}

public class AppAtomic {
    public static void main(String[] args) {
        RunnerAtomic myRunner  = new RunnerAtomic(0);
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
