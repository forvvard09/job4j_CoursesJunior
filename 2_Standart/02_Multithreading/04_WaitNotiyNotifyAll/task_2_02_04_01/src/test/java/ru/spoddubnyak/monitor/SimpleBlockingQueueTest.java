package ru.spoddubnyak.monitor;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Class SimpleBlockingQueueTest for testing method's class SimpleBlockingQueue and realization pattern's producer-consumer.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 15.02.2018
 */
public class SimpleBlockingQueueTest {

    /**
     * Testing patterns producer-consumer, with random count producer and consumer.
     */
    @Test
    public void whenRandomCountProducerRandomCountConsumerThenGetResult() {
        final int expectedCount = 50;
        final int maxSizeQueue = 5;
        final int timOut = 10000;
        List<Integer> externalList = new ArrayList<>();
        SimpleBlockingQueue<Integer> simpleBlockingQueue = new SimpleBlockingQueue<>(expectedCount, maxSizeQueue);
        Threads allThread = new Threads(simpleBlockingQueue, externalList);
        Thread[] producers = new Thread[simpleBlockingQueue.getRandomNumber()];
        Thread[] consumers = new Thread[simpleBlockingQueue.getRandomNumber()];
        for (int i = 0; i < producers.length; i++) {
            producers[i] = allThread.getProducer();
            producers[i].start();
        }
        for (int i = 0; i < consumers.length; i++) {
            consumers[i] = allThread.getConsumer();
            consumers[i].start();
        }
        try {
            Thread.sleep(timOut);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(expectedCount, is(externalList.size()));
        assertThat(0, is(simpleBlockingQueue.getSize()));
        assertThat(expectedCount, is(simpleBlockingQueue.getCountOffer()));
    }

    /**
     * Testing patterns producer-consumer, with one producer and one consumer.
     */
    @Test
    public void whenThenSecond() {
        final int expectedCount = 10;
        final int maxSizeQueue = 2;
        List<Integer> externalList = new ArrayList<>();
        SimpleBlockingQueue<Integer> simpleBlockingQueue = new SimpleBlockingQueue<>(expectedCount, maxSizeQueue);
        Threads allThread = new Threads(simpleBlockingQueue, externalList);
        Thread consumer = allThread.getConsumer();
        Thread producers = allThread.getProducer();
        consumer.start();
        producers.start();
        try {
            consumer.join();
            producers.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(expectedCount, is(externalList.size()));
        assertThat(0, is(simpleBlockingQueue.getSize()));
        assertThat(expectedCount, is(simpleBlockingQueue.getCountOffer()));
    }
}