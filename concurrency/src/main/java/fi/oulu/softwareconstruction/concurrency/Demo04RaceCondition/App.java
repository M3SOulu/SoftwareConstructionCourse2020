package fi.oulu.softwareconstruction.concurrency.Demo04RaceCondition;

class Runner implements Runnable {
    private int id;
    private int count = 0;

    public Runner (int id) {
        this.id = id;
    }

    @Override
    public void run() {
        for (int i=0;i<10000;i++) {
            count++;
        }
    }

    public int getCount() {
        return count;
    }
}

public class App {
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
