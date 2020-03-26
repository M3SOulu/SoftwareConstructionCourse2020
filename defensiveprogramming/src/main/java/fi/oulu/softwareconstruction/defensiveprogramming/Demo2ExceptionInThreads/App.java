package fi.oulu.softwareconstruction.defensiveprogramming.Demo2ExceptionInThreads;

import java.util.Random;

class Runner implements Runnable {
    private int id;
    private Random rng = new Random();

    public Runner (int id) {
        this.id = id;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("In Runner " + id);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            doSomeComputation();
        }
    }

    private void doSomeComputation() throws RuntimeException {
        // Other
        if (this.rng.nextDouble() < 0.1) {
            throw new RuntimeException("Error in Runner " + this.id);
        }
    }
}

public class App {
    public static void main(String[] args) {
        Thread threads[] = initThreads(10);

        startThreads(threads);

        joinThreads(threads);

        System.out.println("Threads Finished");
    }

    private static Thread[] initThreads(int numberOfThreads) {
        Thread threads[] = new Thread[numberOfThreads];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Runner(i));
        }
        return threads;
    }

    private static void startThreads(Thread[] threads) {
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
    }

    private static void joinThreads(Thread[] threads) {
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}