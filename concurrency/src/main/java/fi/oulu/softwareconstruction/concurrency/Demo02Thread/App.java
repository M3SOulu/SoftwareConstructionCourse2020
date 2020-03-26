package fi.oulu.softwareconstruction.concurrency.Demo02Thread;

/*
  Same as previous example but by extending Thread rather than
  implementing Runnable. Not recommend!
 */
class Runner extends Thread {

    @Override
    public void run() {
        while (true) {
            System.out.println("In Other Thread");

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class App {
    public static void main(String[] args) {
        Runner myRunner  = new Runner();
        myRunner.start();

        while (true) {
            System.out.println("In Main Thread");

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
