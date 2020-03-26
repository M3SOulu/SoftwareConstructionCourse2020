package Demo13PingPong;

class PingPongController {
    synchronized void doPing(){
        try {
            Thread.sleep(200);
        } catch(InterruptedException ie) {
            System.out.println("PING interrupted");
        }

        System.out.println("PING");
    }

    synchronized void doPong(){
        try {
            Thread.sleep(500);
        } catch(InterruptedException ie) {
            System.out.println("PONG interrupted");
        }

        System.out.println("PONG");
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
            Thread.sleep(10000);
        } catch(InterruptedException ie) {
        }

        System.exit(0);
    }
}
