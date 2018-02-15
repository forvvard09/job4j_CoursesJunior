package ru.spoddubnyak.monitor;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

/**
 * Class SimpleBlockingQueue internal queue for implementing the design pattern producer - consumer.
 *
 * @param <T> This describes my type parameter
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 13.02.2018
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {

    /**
     * property NEW_LINE -  for a format print to display.
     */
    private static final String NEW_LINE = System.getProperty("line.separator");
    /**
     * property maxSizeInternalQueue maximum size internal queue.
     */
    private final int maxSizeInternalQueue;
    /**
     * property queue for internal queue.
     */
    @GuardedBy("itself")
    private final Deque<T> queue;

    /**
     * property maxCountElements - the maximum number of elements to process.
     */
    private final int maxCountElements;

    /**
     * property countOffer - count elements, which were process internal queue .
     */
    private int countOffer = 0;

    /**
     * Constructor of new object SimpleBlockingQueue.
     *
     * @param maxCountElements value for property maxCountElements
     * @param maxSize          value for property maxSize
     */
    public SimpleBlockingQueue(final int maxCountElements, int maxSize) {
        this.maxCountElements = maxCountElements;
        this.queue = new LinkedList<>();
        this.maxSizeInternalQueue = maxSize;
    }

    /**
     * Getter for property NEW_LINE.
     *
     * @return go to next line
     */
    public String getNewLine() {
        return this.NEW_LINE;
    }

    /**
     * Getter for property countOffer.
     *
     * @return count elements, which were add in internal queue
     */
    public synchronized int getCountOffer() {
        return this.countOffer;
    }

    /**
     * Method return size internal queue.
     *
     * @return size internal queue
     */
    public synchronized int getSize() {
        return this.queue.size();
    }

    /**
     * Getter for property maxCountElements.
     *
     * @return maximum count elements, which can be processed internal queue
     */
    public int getMaxCountElements() {
        return this.maxCountElements;
    }

    /**
     * Method add element in interanl queue.
     *
     * @param value element for add
     */
    public void offer(final T value) {
        synchronized (this.queue) {
            while (this.queue.size() >= this.maxSizeInternalQueue || this.countOffer == this.getMaxCountElements()) {
                try {
                    this.queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (this.queue.offer(value)) {
                this.countOffer++;
            }
            this.queue.notify();
        }
    }

    /**
     * Method returns an element from the beginning of the internal queue with deletion
     * for add him in external list.
     *
     * @return an element from the beginning of the internal queue
     */
    public final T poll() {
        T result;
        synchronized (this.queue) {
            if (this.queue.size() == this.maxSizeInternalQueue) {
                System.out.printf("Queue is full. Wait for the release of the queue...%s", NEW_LINE);
            }
            while (this.queue.size() == 0 && this.queue.peek() == null) {
                try {
                    this.queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            result = this.queue.poll();
            this.queue.notify();
        }
        return result;
    }

    /**
     * Method return random int number.
     *
     * @return random number
     */
    public final int getRandomNumber() {
        final int maxRandomNumber = 11;
        Random random = new Random();
        return random.nextInt(maxRandomNumber + 1);
    }
}