package fi.oulu.softwareconstruction.concurrency.Demo04RaceCondition;

import java.util.ArrayList;
import java.util.Random;

class ListProcess {
    private final int iters = 1000;
    private ArrayList<Integer> list1 = new ArrayList<Integer>();
    private ArrayList<Integer> list2 = new ArrayList<Integer>();

    public void processList1() {
        Random rand = new Random();
        for (int i = 0;i<iters;i++) {
            list1.add(rand.nextInt(1000));
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void processList2() {
        Random rand = new Random();
        for (int i = 0;i<iters;i++) {
            list2.add(rand.nextInt(1000));
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void process() {
        processList1();
        processList2();
    }

    public void printListSizes(){
        System.out.println("List 1 Size:" + list1.size());
        System.out.println("List 2 Size:" + list2.size());
    }
}

public class AppAddToList {
    public static void main(String args[]) {
        ListProcess lp = new ListProcess();

        Thread t1 = new Thread(new Runnable(){
            @Override
            public void run() {
                lp.process();
            }
        });

        Thread t2 = new Thread(new Runnable(){
            @Override
            public void run() {
                lp.process();
            }
        });

        long time1 = System.currentTimeMillis();

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch(InterruptedException ie) {

        }

        long time2 = System.currentTimeMillis();

        lp.printListSizes();
        System.out.println("Time Spent :" + (time2-time1));
    }
}
