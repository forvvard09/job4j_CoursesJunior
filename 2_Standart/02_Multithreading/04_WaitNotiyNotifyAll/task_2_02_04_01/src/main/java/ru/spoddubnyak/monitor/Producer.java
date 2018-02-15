package ru.spoddubnyak.monitor;

/**
 * Class Producer implement object producer, and add elements in internal queue.
 *
 * @param <T> This describes my type parameter
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 14.02.2018
 */
public class Producer<T> {

    /**
     * property objectQueue - link for object SimpleBlockingQueue.
     */
    private SimpleBlockingQueue<T> objectQueue;

    /**
     * Constructor of new object Producer.
     *
     * @param objectQueue value for property objectQueue
     */
    public Producer(SimpleBlockingQueue<T> objectQueue) {
        this.objectQueue = objectQueue;
    }

    /**
     * Method add element in internal queue and print information on operation.
     *
     * @param elementForAdd - value element for add in internal queue
     */
    public void offer(T elementForAdd) {
        System.out.printf("Producer, threadId=%s: add number: %s to internal queue.%s", Thread.currentThread().getId(), elementForAdd, this.objectQueue.getNewLine());
        this.objectQueue.offer(elementForAdd);
    }

    /**
     * Method return link for property objectQueue.
     *
     * @return link for property objectQueue
     */
    public SimpleBlockingQueue getObjectQueue() {
        return this.objectQueue;
    }
}