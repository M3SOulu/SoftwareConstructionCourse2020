package fi.oulu.softwareconstruction.concurrency.Demo07Pools;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Runner implements Runnable{

    private int id;
    public Runner(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        Random random = new Random();
        System.out.println("Runner "+ id+ " Started");

        for (int i = 0;i<5;i++) {
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Runner "+ id+ " Finished");
    }
}

public class App {
    public static void main(String args[]) {
        ExecutorService exec = Executors.newFixedThreadPool(3);

        for (int i = 0;i< 10;i++) {
            exec.submit(new Runner(i));
        }

        exec.shutdown();

        try {
            exec.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All Threads finished...");
    }
}
