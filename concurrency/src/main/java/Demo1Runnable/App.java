package Demo1Runnable;

class Runner implements Runnable {

    @Override
    public void run() {
        while (true) {
            System.out.println("In Runnable");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

public class App {
    public static void main(String[] args) {

        Runner myRunner  = new Runner();
        Thread t1 = new Thread(myRunner);
        t1.start();
        while (true) {
            System.out.println("In Main Thread");

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
