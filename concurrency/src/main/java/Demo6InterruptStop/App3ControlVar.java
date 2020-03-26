package Demo6InterruptStop;

import java.util.Scanner;

class Runner3 implements Runnable {
    private volatile boolean isRunning = true;
    private int id;

    public Runner3 (int id) {
        this.id = id;
    }

    @Override
    public void run() {
        while (isRunning) {
            System.out.println("In Runner "+id);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }

        System.out.println("Thread "+ id +" finished.");
    }

    public void stopRunner() {
        isRunning = false;
    }
}

public class App3ControlVar {
    public static void main(String[] args) {
        Thread threads[] = new Thread[10];
        Runner3 runners[] = new Runner3[10];

        for (int i = 0;i< threads.length;i++) {
            runners[i] = new Runner3(i);
            threads[i] = new Thread(runners[i]);
        }

        System.out.println("Press Enter to start... Press another Enter to Stop...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        for (int i = 0;i< threads.length;i++) {
            threads[i].start();
        }

        scanner.nextLine();

        for (int i = 0;i< threads.length;i++) {
            runners[i].stopRunner();
        }
    }
}
