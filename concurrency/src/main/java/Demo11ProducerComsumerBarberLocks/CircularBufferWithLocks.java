package Demo11ProducerComsumerBarberLocks;
/*
    An implementation of circular buffer. The size of buffer is given in the constructor.

    Methods:

    public synchronized void put(Object obj)

        puts a new object into buffer if space left - otherwise blocks

    public synchronized Object take()

        takes an object from buffer if any - otherwise blocks

    Synchronization uses Java's lock objects.

*/

import java.util.concurrent.locks.*;

public class CircularBufferWithLocks {
    protected final Object[] buffer;
    protected int putPtr = 0;
    protected int takePtr = 0;
    protected int queuedItems = 0;

    private final Lock bufferLock;
    private final Condition notFull;
    private final Condition notEmpty;

    public CircularBufferWithLocks(int size) throws IllegalArgumentException {
        // Check first that size is OK
        if( size <= 0)
            throw new IllegalArgumentException();

        buffer = new Object[size];

        // Create synchrinization objects
        bufferLock = new ReentrantLock();
        notFull = bufferLock.newCondition();
        notEmpty = bufferLock.newCondition();
    }

    public int getCapacity() {
        return buffer.length;
    }

    public synchronized int getCurrentSize() {
        return queuedItems;
    }

    public void put(Object obj) throws InterruptedException{

        // Acquire lock
        bufferLock.lock();

        try {

            // Wait while buffer is full
            while(queuedItems == buffer.length)
                notFull.await();

            // Put object in buffer
            buffer[putPtr] = obj;

            // Increase putPtr cyclically and increase current size
            putPtr = (putPtr +1) % buffer.length;
            queuedItems++;

            // Signal that buffer not empty
            notEmpty.signal();
        }
        finally {
            // release lock
            bufferLock.unlock();
        }
    }

    public Object take() throws InterruptedException {

        Object retObj = null;

        // Acquire lock
        bufferLock.lock();

        try {
            // Wait while buffer is empty
            while(queuedItems == 0)
                notEmpty.await();

            retObj = buffer[takePtr];

            // Increase takePtr cyclically and decrease current size
            takePtr = (takePtr +1) % buffer.length;
            queuedItems--;

            // Signal that buffer not full
            notFull.signal();
        }
        finally {
            // release lock
            bufferLock.unlock();
        }
        return retObj;
    }
}
