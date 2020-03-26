package Demo11ProducerComsumerBarberLocks;
/*
    Abstract class for consumer using CircularBuffer
*/
public abstract class ConsumerWithLocks {
    private CircularBufferWithLocks cbBuffer;

    public ConsumerWithLocks(CircularBufferWithLocks cb) {
        cbBuffer = cb;
    }

    protected abstract void consume(Object obj);

    public Object takeObject() throws InterruptedException {
        return cbBuffer.take();
    }

    public int getNumStored() {
        return cbBuffer.getCurrentSize();
    }
}
