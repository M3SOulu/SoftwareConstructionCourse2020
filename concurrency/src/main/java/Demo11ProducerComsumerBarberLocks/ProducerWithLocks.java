package Demo11ProducerComsumerBarberLocks;
/*
    Abstract class for producer using CircularBuffer
*/

public abstract class ProducerWithLocks{
    private CircularBufferWithLocks cbBuffer;

    public ProducerWithLocks(CircularBufferWithLocks cb) {
        cbBuffer = cb;
    }

    protected abstract Object produce();

    public void putObject(Object obj) throws InterruptedException {
        cbBuffer.put(obj);
    }
}
