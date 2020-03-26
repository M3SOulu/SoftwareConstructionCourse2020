/*
    An abstract class that sets the framework for Readers and Writers
    pattern. Uses Java's ReadWriteLock.

    Policy: Several active readers can operate simultaneously,
            Only one writer can operate at one time (no readers),
            Oldest waiting writer is preferred over readers


    Abstract methods readOperation() and writeOperation()
    must be overridden in subclass. They realize the actual
    operations.

*/
package fi.oulu.softwareconstruction.concurrency.Demo12ReaderWriterLocks;
import java.util.concurrent.locks.*;

abstract class ReadAndWriteLocks {
    /*  Abstract methods that implement the actual
        read and write operations. Must be overridden
        in the subclass */
    protected abstract void readOperation();
    protected abstract void writeOperation();

    // Fair lock
    final private ReentrantReadWriteLock rrwl = new ReentrantReadWriteLock(true);
    final private Lock readLock = rrwl.readLock();
    final private Lock writeLock = rrwl.writeLock();

    /*  Read and write methods realizing the
        before after pattern for read and write */
    public void read() {
        beforeRead();

        try {
            readOperation();
        }
        finally {
            afterRead();
        }
    }

    public void write() {
        beforeWrite();

        try {
            writeOperation();
        }
        finally {
            afterWrite();
        }
    }

    // Before operations
    protected void beforeRead() {
        // Try to acquire read lock
        readLock.lock();
    }

    protected void beforeWrite() {
        // Try to acquire write lock
        writeLock.lock();
    }

    // After operations
    protected void afterRead() {
        // Release read lock
        readLock.unlock();
    }

    protected void afterWrite() {
        // Release read lock
        writeLock.unlock();
    }
}
