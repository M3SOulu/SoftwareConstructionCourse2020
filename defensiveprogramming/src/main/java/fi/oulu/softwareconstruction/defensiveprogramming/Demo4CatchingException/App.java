package fi.oulu.softwareconstruction.defensiveprogramming.Demo4CatchingException;

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

            try {
                tryFail();
            } catch (RuntimeException e) {
                System.out.println("Exception caught in Runner " + id);
            }
        }
    }

    private void tryFail() {
        if (this.rng.nextDouble() < 0.5) {
            throw new RuntimeException("Error in Runner " + this.id);
        } else {
            System.out.println("Operation successful in Runner " + this.id);
        }
    }
}

class ThreadPool {
    private Thread[] threads;

    public ThreadPool(int numThreads) {
        threads = new Thread[numThreads];
        initThreads();
    }

    private void initThreads() {
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Runner(i));
        }
    }

    public void startThreads() {
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
    }

    public void joinThreads() {
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class App {
    public static void main(String[] args) {
        ThreadPool threads = new ThreadPool(10);
        threads.startThreads();
        threads.joinThreads();
        System.out.println("Threads Finished");
    }
}