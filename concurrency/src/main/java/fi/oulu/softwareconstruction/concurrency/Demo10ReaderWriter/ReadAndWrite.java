/*
    An abstract class that sets the framework for Readers and Writers
    pattern. Uses Before - After Pattern.

    Policy: Several active readers can operate simultaneously,
            Only one writer can operate at one time (no readers),
            If a writer is waiting, no new readers accepted

    Abstract methods readOperation() and writeOperation()
    must be overridden in subclass. They realize the actual
    operations.

*/

package fi.oulu.softwareconstruction.concurrency.Demo10ReaderWriter;

abstract class ReadAndWrite {
    protected int numActiveReaders = 0;
    protected int numActiveWriters = 0;

    protected int numWaitingReaders = 0;
    protected int numWaitingWriters = 0;

    /*  Abstract methods that implement the actual
        read and write operations. Must be overridden
        in the subclass */
    protected abstract void readOperation();
    protected abstract void writeOperation();

    /*  Read and write methods realizing the
        before after pattern for read and write */
    public void read() throws InterruptedException {
        beforeRead();

        try {
            readOperation();
        }
        finally {
            afterRead();
        }
    }

    public void write() throws InterruptedException {
        beforeWrite();

        try {
            writeOperation();
        }
        finally {
            afterWrite();
        }
    }

    /* Protocols for readers and writers - can be
        overridden in subclasses in implementations */
    protected boolean readerAdmissible() {
        boolean condition;

        // Put here condition
        condition = (numWaitingWriters == 0) &&
                    (numActiveWriters == 0);

        return condition;
    }

    protected boolean writerAdmissible() {
        boolean condition;

        // Put here condition
        condition = (numActiveReaders == 0) &&
                    (numActiveWriters == 0);

        return condition;
    }

    // Before operations
    protected synchronized void beforeRead()
        throws InterruptedException {
        numWaitingReaders++;

        while(!readerAdmissible()) {
            try {
                wait();
            }
            catch(InterruptedException ie) {
                // This reader no more waiting
                numWaitingReaders--;
                throw ie;
            }
        }

        // This reader turned into active
        numWaitingReaders--;
        numActiveReaders++;
    }

    protected synchronized void beforeWrite()
        throws InterruptedException {
        numWaitingWriters++;

        while(!writerAdmissible()) {
            try {
                wait();
            }
            catch(InterruptedException ie) {
                // This Writer no more waiting
                numWaitingWriters--;
                throw ie;
            }

        }

        // This Writer turned into active
        numWaitingWriters--;
        numActiveWriters++;

    }

    // After operations
    protected synchronized void afterRead() {
        numActiveReaders--;
        notifyAll();
    }

    protected synchronized void afterWrite() {
        numActiveWriters--;
        notifyAll();
    }

}
