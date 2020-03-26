package fi.oulu.softwareconstruction.concurrency.Demo03MultipleThreads;

class Runner implements Runnable {
    private int id;

    public Runner (int id) {
        this.id = id;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("In Runner "+id);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class App {
    public static void main(String[] args) {
        Thread threads[] = new Thread[10];
        for (int i = 0;i< threads.length;i++) {
            threads[i] = new Thread(new Runner(i));
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

        System.out.println("Threads Finished");
    }
}
