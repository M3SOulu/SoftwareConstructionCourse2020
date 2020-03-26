package Demo12ReaderWriterLocks;
import java.util.*;

class StrReadWrite extends ReadAndWriteLocks {
    protected int readerID = 0;
    protected int writerID = 0;
    protected String rwString = "";

    public StrReadWrite() {
        super();
    }

    protected void readOperation() {
        int thisID;

        synchronized(this) {
            readerID++;
            thisID = readerID;
        }

        System.out.println("Reader " + thisID + " reads ");

        for(int i = 0; i < 3; i++) {

            try {
                Thread.sleep(1000);
            }
            catch(InterruptedException ie) {
                ;
            }
        }
    }

    protected void writeOperation() {
        int thisID;

        writerID++;
        thisID = writerID;

        for(int i = 0; i < 3; i++) {
            try {
                Thread.sleep(1000);
            }
            catch(InterruptedException ie) {
                ;
            }
        }

        System.out.println("writer " + thisID + " wrote ");
    }

}

class ReaderThread extends Thread {
    private StrReadWrite rwCtrl;

    public ReaderThread(StrReadWrite srw) {
        super();
        rwCtrl = srw;
    }

    public void run() {
        rwCtrl.read();
    }

}

class WriterThread extends Thread {
    private StrReadWrite rwCtrl;

    public WriterThread(StrReadWrite srw) {
        super();
        rwCtrl = srw;
    }

    public void run() {
        rwCtrl.write();
    }
}

public class StrReadWriteLockTest {
    static StrReadWrite readWriteCtrl;

    public static void main(String [] argv ) {
        readWriteCtrl = new StrReadWrite();

        String readwrites = "rrrwrrrrrwwrrrwwwwwrrwrwr";
        for (char c: readwrites.toCharArray()) {
            if (c == 'r') {
                ReaderThread rt;
                rt = new ReaderThread(readWriteCtrl);
                rt.start();
            }

            else if (c=='w') {
                WriterThread wt;
                wt = new WriterThread(readWriteCtrl);
                wt.start();

                try {
                    Thread.sleep(3500);
                }
                catch(InterruptedException ex) {
                    System.out.println(ex.toString());
                }
            }
        }
    }
}
