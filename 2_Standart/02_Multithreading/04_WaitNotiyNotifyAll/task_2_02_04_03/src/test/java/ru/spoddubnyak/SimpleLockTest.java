package ru.spoddubnyak;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class SimpleLockTest for testing method's class SimpleLock implements interface Lock.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 12.03.2018
 */
public class SimpleLockTest {

    /**
     * property Semaphore.
     */
    private static final int TIMER_SLEEP = 5000;
    /**
     * property Semaphore.
     */
    private SimpleLock simpleLock = new SimpleLock();

    /**
     * Test for methods lock(), tryLock() and unlock().
     */
    @Test
    public void whenLockUnlockObjectThenExpectedsStateStateLock() {
        assertThat(false, is(simpleLock.isLock()));
        simpleLock.lock();
        assertThat(true, is(simpleLock.isLock()));
        assertThat(false, is(simpleLock.tryLock()));
        simpleLock.unlock();
        assertThat(false, is(simpleLock.isLock()));
        assertThat(true, is(simpleLock.tryLock()));

    }

    /**
     * Test for methods newCondition().
     */
    @Test(expected = UnsupportedOperationException.class)
    public void whenRunMethodThenExpectedError() {
        simpleLock.lock();
        simpleLock.newCondition();
    }

    /**
     * Test for method simpleLock.tryLock(long time, TimeUnit unit).
     */
    @Test
    public void whenTryLockTimeThenExpectedStateLock() {
        simpleLock.lock();
        try {
            assertThat(false, is(simpleLock.tryLock(TIMER_SLEEP, TimeUnit.MILLISECONDS)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        simpleLock.unlock();
        try {
            assertThat(true, is(simpleLock.tryLock(TIMER_SLEEP, TimeUnit.MILLISECONDS)));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test for method lock(), when thread is isInterrupted() == ture.
     */
    @Test
    public void whenThen() {
        assertThat(false, is(simpleLock.isLock()));
        Thread.currentThread().interrupt();
        simpleLock.lock();
        assertThat(false, is(simpleLock.isLock()));
    }

    /**
     * Test for method lockInterruptibly().
     */
    @Test
    public void whenThen2() {
        simpleLock.lock();
        simpleLock.unlock();
        try {
            simpleLock.lockInterruptibly();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(true, is(simpleLock.isLock()));
        assertThat(false, is(simpleLock.tryLock()));
    }
}
