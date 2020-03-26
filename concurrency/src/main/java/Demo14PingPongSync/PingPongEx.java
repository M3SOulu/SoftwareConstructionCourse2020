package Demo14PingPongSync;

class PingPongController {
    synchronized void doPing() {
        try {
            Thread.sleep(200);
            System.out.println("PING");
            notify();
            wait();
        }
        catch(InterruptedException ie) {
            System.out.println("PING interrupted");
        }
    }

    synchronized void doPong() {
        try {
            Thread.sleep(200);

            System.out.println("PONG");
            notify();
            wait();
        } catch(InterruptedException ie) {
            System.out.println("PONG interrupted");
        }
    }
}

class PingThread extends Thread {
    PingPongController myController;

    public PingThread(PingPongController ppc) {
        myController = ppc;
    }

    public void run() {
        while(true) {
            myController.doPing();
        }
    }
}

class PongThread extends Thread {
    PingPongController myController;

    public PongThread(PingPongController ppc) {
        myController = ppc;
    }

    public void run() {
        while(true) {
            myController.doPong();
        }
    }
}

public class PingPongEx {
    public static void main(String[] args) {
        PingPongController controller = new PingPongController();

        PingThread pingThr = new PingThread(controller);
        PongThread pongThr = new PongThread(controller);

        pingThr.start();
        pongThr.start();

        try {
            Thread.sleep(5000);
        } catch(InterruptedException ie) {
        }

        System.exit(0);
    }
}
