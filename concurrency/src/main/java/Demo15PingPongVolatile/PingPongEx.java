package Demo15PingPongVolatile;

class PingPongController {
    private volatile boolean pingDone=false;

    void doPing(){
        try {
            while(pingDone) {
            }

            Thread.sleep(200);
            System.out.println("PING");
            pingDone = true;
        } catch(InterruptedException ie) {
            return;
        }
    }

    void doPong(){
        try {
            Thread.sleep(500);
            while(!pingDone) {
            }
            System.out.println("PONG");
            pingDone = false;
        } catch(InterruptedException ie) {
            return;
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
            Thread.sleep(10000);
        } catch(InterruptedException ie) {
        }

        System.exit(0);
    }
}
