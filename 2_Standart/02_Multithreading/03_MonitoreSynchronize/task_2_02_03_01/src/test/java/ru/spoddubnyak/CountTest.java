package ru.spoddubnyak;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests class Count.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 12.01.2018
 */
public class CountTest {
    /**
     * Test testing work in 2 threads.
     *
     * @throws InterruptedException errors with threads
     */
    @Test
    public void whenExecute2ThreadThen2() throws InterruptedException {
        //Создаем счетчик.
        final Count count = new Count();
        //Создаем нити.
        Thread first = new ThreadCount(count);
        Thread second = new ThreadCount(count);
        //Запускаем нити.
        first.start();
        second.start();
        //Заставляем главную нить дождаться выполнения наших нитей.
        first.join();
        second.join();
        //Проверяем результат.
        assertThat(count.getValue(), is(2));

    }

    /**
     * A class describes a thread with a counter.
     */
    private class ThreadCount extends Thread {
        /**
         * property -  class fo work.
         */
        private final Count count;

        /**
         * Constructor - creates a new object CountSymbolsInLine by specififcation.
         *
         * @param count class for worker
         */
        private ThreadCount(final Count count) {
            this.count = count;
        }

        @Override
        public void run() {
            this.count.increment();
        }
    }

}
