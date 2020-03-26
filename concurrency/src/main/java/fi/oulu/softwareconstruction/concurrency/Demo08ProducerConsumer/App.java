package fi.oulu.softwareconstruction.concurrency.Demo08ProducerConsumer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

class ProducerConsumer{
    private ArrayBlockingQueue<Integer> data = new ArrayBlockingQueue<Integer>(10);
    public void produce() {
        Random random = new Random();
        try {
            data.put(random.nextInt(1000));
            System.out.println("Inserted data");

            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void consume() {
        try {
            int val = data.take();
            System.out.println("Value Taken: "+val+"  List size is: "+data.size());

            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class App {
    public static void main(String args[]) {
        ProducerConsumer pc = new ProducerConsumer();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                pc.produce();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                pc.consume();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch(InterruptedException ie) {
        }
    }
}
