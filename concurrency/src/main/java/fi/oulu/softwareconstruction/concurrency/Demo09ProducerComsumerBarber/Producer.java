/*
    Abstract class for producer using CircularBuffer
*/
package fi.oulu.softwareconstruction.concurrency.Demo09ProducerComsumerBarber;

public abstract class Producer{
    private CircularBuffer cbBuffer;

    public Producer(CircularBuffer cb) {
        cbBuffer = cb;
    }

    protected abstract Object produce();

    public void putObject(Object obj) throws InterruptedException {
        cbBuffer.put(obj);
    }
}
