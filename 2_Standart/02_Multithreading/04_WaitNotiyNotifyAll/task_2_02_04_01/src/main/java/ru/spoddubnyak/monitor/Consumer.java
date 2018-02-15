package ru.spoddubnyak.monitor;


import java.util.List;

/**
 * Class Consumer implement object consummer, and put elements out interanl queue and add in external list.
 *
 * @param <T> This describes my type parameter
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 14.02.2018
 */
public class Consumer<T> {

    /**
     * property externalList - List<T> for add element from internal queue.
     */
    private List<T> externalList;
    /**
     * property objectQueue - link for object SimpleBlockingQueue.
     */
    private SimpleBlockingQueue objectQueue;

    /**
     * Constructor of new object Consumer.
     *
     * @param objectQueue  value for property objectQueue
     * @param externalList value for property externalList
     */
    public Consumer(SimpleBlockingQueue objectQueue, List<T> externalList) {
        this.externalList = externalList;
        this.objectQueue = objectQueue;
    }

    /**
     * Method add element in external list from internal queue and print information on operation.
     */
    public void add() {
        T numberForAdd = (T) this.objectQueue.poll();
        System.out.printf("Consumer, threadId=%s: get number: %s out internal queue and to external list.%s", Thread.currentThread().getId(), numberForAdd, this.objectQueue.getNewLine());
        this.externalList.add(numberForAdd);
    }

    /**
     * Method return link for property externalList.
     *
     * @return link for property externalList
     */
    public List getExternalList() {
        return this.externalList;
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