package Demo6InterruptStop;

import java.util.Scanner;

class Runner2 extends Thread {

    private int id;

    public Runner2 (int id) {
        this.id = id;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("In Runner "+id);

            // try {
            //     Thread.sleep(100);
            // } catch (InterruptedException e) {
            //     e.printStackTrace();
            //     break;
            // }

            if (this.isInterrupted()) {
                System.out.println("Interrupted "+id);
                break;
            }
        }
    }
}

public class App2Interrupt {
    public static void main(String[] args) {
        Runner2 threads[] = new Runner2[10];
        for (int i = 0;i< threads.length;i++) {
            threads[i] = new  Runner2(i);
        }

        System.out.println("Press Enter to start... Press another Enter to Stop...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        for (int i = 0;i< threads.length;i++) {
            threads[i].start();
        }

        scanner.nextLine();

        for (int i = 0;i< threads.length;i++) {
            threads[i].interrupt();
        }
    }
}
