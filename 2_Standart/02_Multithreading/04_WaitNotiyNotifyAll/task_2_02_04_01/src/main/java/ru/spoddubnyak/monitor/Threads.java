package ru.spoddubnyak.monitor;

import java.util.List;

/**
 * Class Threads create threads for producer and consumer.
 *
 * @param <T> This describes my type parameter
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 14.02.2018
 */
public class Threads<T> {

    /**
     * property objectQueue - link for object SimpleBlockingQueue.
     */
    private SimpleBlockingQueue objectQueue;

    /**
     * property objectQueue - link for object SimpleBlockingQueue.
     */
    private List<T> externalList;

    /**
     * Constructor of new object Consumer.
     *
     * @param objectQueue  value for property objectQueue
     * @param externalList value for property externalList
     */
    public Threads(SimpleBlockingQueue objectQueue, List<T> externalList) {
        this.objectQueue = objectQueue;
        this.externalList = externalList;
    }

    /**
     * Method create and return object ProducerThread.
     *
     * @return object ProducerThread
     */
    public Thread getProducer() {
        return new ProducerThread();
    }

    /**
     * Method create and return object ProducerThread.
     *
     * @return object ProducerThread
     */
    public Thread getConsumer() {
        return new ConsumerThread();
    }

    /**
     * Inner class ProducerThread for create thread for producer.
     */
    private class ProducerThread extends Thread {

        @Override
        public void run() {
            Producer producer = new Producer(objectQueue);

            while (producer.getObjectQueue().getCountOffer() < producer.getObjectQueue().getMaxCountElements()) {
                producer.offer(objectQueue.getRandomNumber());
            }
        }
    }

    /**
     * Inner class ConsumerThread for create thread for consumer.
     */
    private class ConsumerThread extends Thread {
        @Override
        public void run() {
            Consumer consumer = new Consumer(objectQueue, externalList);
            while (consumer.getExternalList().size() < consumer.getObjectQueue().getMaxCountElements()) {
                consumer.add();
            }
        }
    }
}