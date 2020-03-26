package Demo16PingPongLocks;

import java.util.concurrent.locks.*;

class PingPongController {
    private final Lock lock;
    private final Condition ping;
    private final Condition pong;

    public PingPongController(){
        lock = new ReentrantLock();
        ping = lock.newCondition();
        pong = lock.newCondition();
    }

    //synchronized
    void doPing(){
        lock.lock();
        try {
            Thread.sleep(200);

            System.out.println("PING");

            pong.signal();
            ping.await();
        } catch(InterruptedException ie){
            return;
        } finally {
            lock.unlock();
        }

    }

    //synchronized
    void doPong(){
        lock.lock();
        try {
            Thread.sleep(500);
            System.out.println("PONG");

            ping.signal();
            pong.await();
        } catch(InterruptedException ie){
            return;
        } finally {
            lock.unlock();
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

public class PingPongExSolLock {
    public static void main(String[] args) {
        PingPongController controller = new PingPongController();

        PingThread pingThr = new PingThread(controller);
        PongThread pongThr = new PongThread(controller);

        pingThr.start();
        pongThr.start();

        try {
            Thread.sleep(15000);
        } catch(InterruptedException ie) {
        }

        System.exit(0);
    }
}
