package ru.spoddubnyak;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class SimpleLockTest for testing method's class SimpleLock implements interface Lock.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 2.0
 * @since 27.03.2018
 */
public class SimpleLockTest {
    /**
     * property timer for delay 2sec for testing.
     */
    private final int timerOne = 2000;
    /**
     * property timer for delay 5sec for testing.
     */
    private final int timerTwo = 5000;
    /**
     * property object SimpleLock for testing simple lock.
     */
    private SimpleLock simpleLock = new SimpleLock();

    /**
     * Test for methods lock() and unlock().
     */
    @Test
    public void whenLockUnlockObjectThenExpectedsStateStateLock() {
        assertThat(false, is(simpleLock.getIsLock()));
        simpleLock.lock();
        assertThat(true, is(simpleLock.getIsLock()));
        simpleLock.unlock();
        assertThat(false, is(simpleLock.getIsLock()));
    }

    ;

    /**
     * Test for methods lock() and unlock() for two threads.
     */
    @Test
    public void whenTwoThreadsLockUnlockObjectThenExpectedsStateStateLock() {
        MyThred thredOne = new MyThred();
        MyThred thredSecond = new MyThred();
        assertThat(false, is(simpleLock.getIsLock()));
        simpleLock.lock();
        assertThat(true, is(simpleLock.getIsLock()));
        thredOne.start();
        try {
            thredOne.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        simpleLock.unlock();
        thredSecond.start();
        try {
            thredSecond.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(false, is(simpleLock.getIsLock()));
    }

    /**
     * Test for methods lock() and unlock() for two threads.
     */
    @Test
    public void whenLockAlreadyLockThenExpectedResult() {
        MyThredWait thredOne = new MyThredWait();
        assertThat(false, is(simpleLock.getIsLock()));
        simpleLock.lock();
        assertThat(true, is(simpleLock.getIsLock()));
        thredOne.start();
        try {
            Thread.sleep(timerTwo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        simpleLock.unlock();
        try {
            Thread.sleep(timerOne);
            Thread.sleep(timerOne);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(false, is(simpleLock.getIsLock()));
    }

    /**
     * Inner Class MyThred for testing.
     */
    class MyThred extends Thread {
        @Override
        public void run() {
            final int predel = 10;
            int i = 0;
            while (i < predel) {
                simpleLock.unlock();
                if (simpleLock.getIsLock()) {
                    System.out.printf("%s:%s %s.%s", "Thred id", Thread.currentThread().getId(), "SimpleLock is busy other thread", System.getProperty("line.separator"));
                } else {
                    System.out.printf("%s:%s %s.%s", "Thred id", Thread.currentThread().getId(), "SimpleLock is free", System.getProperty("line.separator"));
                    simpleLock.lock();
                    System.out.printf("%s. %s:%s.%s", "SimpleLock is bysy", "Thred id", Thread.currentThread().getId(), System.getProperty("line.separator"));
                    break;
                }
                i++;
            }
            simpleLock.unlock();
        }
    }

    /**
     * Inner Class MyThredWait for testing.
     */
    class MyThredWait extends Thread {
        @Override
        public void run() {
            if (simpleLock.getIsLock()) {
                simpleLock.lock();
                System.out.printf("%s. %s:%s.%s", "SimpleLock successfully locked", "Thread id", Thread.currentThread().getId(), System.getProperty("line.separator"));
                try {
                    Thread.sleep(timerOne);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            simpleLock.unlock();
            if (!simpleLock.getIsLock()) {
                System.out.printf("%s. %s:%s. %s", "SimpleLock successfully unlocked", "Thread id", Thread.currentThread().getId(), System.getProperty("line.separator"));
            }
        }

        ;
    }
}
