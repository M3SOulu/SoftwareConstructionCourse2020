/*
    Abstract class for consumer using CircularBuffer
*/

package Demo9ProducerComsumerBarber;
public abstract class Consumer{
    private CircularBuffer cbBuffer;

    public Consumer(CircularBuffer cb) {
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
