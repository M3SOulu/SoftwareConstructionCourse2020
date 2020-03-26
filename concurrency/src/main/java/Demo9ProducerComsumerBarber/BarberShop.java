package Demo9ProducerComsumerBarber;

/*
    A monitor solution to Sleeping Barber problem:

    Solution uses a circular buffer with synchronized put and take methods.
    Abstract classes Consumer and Producer are inherited.
*/

import java.util.*;

class Customer {
    private int custNo;

    public Customer(int no) {
        custNo = no;
    }

    public String toString() {
        return "Customer number " + custNo;
    }
}

class Barber extends Consumer implements Runnable {
    private String name = "Monitor Barber";
    private int maxCutTime;

    private Random rndGen;

    public Barber(CircularBuffer cb, int maxTime) {
        super(cb);

        maxCutTime = maxTime;

        rndGen = new Random();
    }

    protected void consume(Object obj) {
        Customer c = (Customer) obj;

        System.out.println(name + " starts cutting the hair of " + c + "\n");

        try {
            Thread.sleep(1000 + Math.abs(rndGen.nextInt(1000 * maxCutTime)));
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }

        System.out.println(name + " stopped cutting the hair of " + c + "\n");
    }

    public void run() {
        Object obj;

        while (true) {
            try {
                if (getNumStored() == 0)
                    System.out.println(name + " sleeping.\n");

                obj = takeObject();

                consume(obj);

            } catch (InterruptedException ie) {
                break;
            }
        }
    }
}

class CustomerAppearance extends Producer implements Runnable {
    private int maxAppearTime;

    private int CustomerNum = 0;
    private Random rndGen;

    public CustomerAppearance(CircularBuffer cb, int maxTime) {
        super(cb);
        maxAppearTime = maxTime;
        rndGen = new Random();
    }

    protected Object produce() {
        Object obj;

        try {
            Thread.sleep(1000 + Math.abs(rndGen.nextInt(1000 * maxAppearTime)));
        } catch (InterruptedException ie) {
            ;
        }

        CustomerNum++;

        obj = new Customer(CustomerNum);
        return obj;
    }

    public void run() {

        Customer c;

        while (true) {
            try {
                c = (Customer) produce();
                putObject(c);

                System.out.println("" + c + "arrived.+\n");

            } catch (InterruptedException ie) {
                break;
            }
        }
    }
}

public class BarberShop {
    static CustomerAppearance caQueue = null;
    static Barber theBarber;
    static Thread BarberThread;
    static Thread CustomerThread;
    static CircularBuffer chairs = null;

    public static void main(String[] argv) {
        try {
            chairs = new CircularBuffer(4);
        } catch (IllegalArgumentException iae) {
            System.out.println("Invalid constrution of circular buffer ");
            iae.printStackTrace();

            System.exit(-1);
        }

        caQueue = new CustomerAppearance(chairs, 2);
        theBarber = new Barber(chairs, 6);

        BarberThread = new Thread(theBarber);
        CustomerThread = new Thread(caQueue);

        CustomerThread.start();
        BarberThread.start();
    }
}
