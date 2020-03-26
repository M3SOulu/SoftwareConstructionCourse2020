package fi.oulu.softwareconstruction.defensiveprogramming.Demo5RestartThread;

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

            tryFail();
        }
    }

    private void tryFail() {
        if (this.rng.nextDouble() < 0.5) {
            throw new RuntimeException("Error in Runner " + this.id);
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

    public void checkThreads() {
        for (int i = 0; i < threads.length; i++) {
            Thread.State s = threads[i].getState();
            System.out.println("Thread " + i + ": " + s);
            if (s == Thread.State.TERMINATED) {
                System.out.println("Restart Thread " + i);
                threads[i] = new Thread(new Runner(i));
                threads[i].start();
            }
        }
    }
}

public class App {
    public static void main(String[] args) {
        ThreadPool threads = new ThreadPool(10);
        threads.startThreads();
        
        while (true) {
            threads.checkThreads();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
        System.out.println("Threads Finished");
    }
}