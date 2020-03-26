package Demo10ReaderWriter;

class StrReadWrite extends ReadAndWrite {
    protected int readerID = 0;
    protected int writerID = 0;

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
             ie.printStackTrace();
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
                ie.printStackTrace();
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
        try {
            rwCtrl.read();
        }
        catch(InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}

class WriterThread extends Thread {
    private StrReadWrite rwCtrl;

    public WriterThread(StrReadWrite srw) {
        super();
        rwCtrl = srw;
    }

    public void run() {
        try {
            rwCtrl.write();
        }
        catch(InterruptedException ie) {
            ;
        }
    }
}

public class StrReadWriteTest {
    public static void main(String [] argv ) {
        StrReadWrite readWriteCtrl = new StrReadWrite();
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
