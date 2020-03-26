package fi.oulu.softwareconstruction.concurrency.Demo06InterruptStop;

import java.util.Scanner;

class Runner1 implements Runnable {
    private int id;

    public Runner1 (int id) {
        this.id = id;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("In Runner "+id);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}


public class AppStop {
    public static void main(String[] args) {
        Thread threads[] = new Thread[10];

        for (int i = 0;i< threads.length;i++) {
            threads[i] = new Thread(new Runner1(i));
        }

        System.out.println("Press Enter to start... Press another Enter to Stop...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        for (int i = 0;i< threads.length;i++) {
            threads[i].start();
        }

        scanner.nextLine();

        for (int i = 0;i< threads.length;i++) {
            threads[i].stop();
        }
    }
}
