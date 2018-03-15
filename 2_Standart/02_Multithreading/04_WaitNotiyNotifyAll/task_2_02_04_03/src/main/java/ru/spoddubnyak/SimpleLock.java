package ru.spoddubnyak;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Class SimpleLock implement interface Lock.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 12.03.2018
 */
public class SimpleLock implements Lock {
    /**
     * property UNIVERSAL_TIMER - universal timer for  .
     */
    private static final long UNIVERSAL_TIMER = 333;
    /**
     * property Semaphore.
     */
    private final Semaphore semaphore;
    /**
     * property lock - object free flag.
     */
    private boolean lock;

    /**
     * Constructor it creates a new object ThreadPool with the specified.
     */
    public SimpleLock() {
        this.semaphore = new Semaphore(1);
        this.lock = false;
    }

    /**
     * Gettter for property lock, return state of object, false - object is free, true - isn't free.
     *
     * @return state of object, false - object is free, true - isn't free
     */
    public boolean isLock() {
        return this.lock;
    }

    @Override
    public void lock() {
        while (!this.lock && !Thread.currentThread().isInterrupted()) {
            try {
                semaphore.acquire();
                this.lock = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        boolean result = tryLock();
        if (!Thread.currentThread().isInterrupted() && !result) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public boolean tryLock() {
        boolean result = false;
        if (!this.lock && !Thread.currentThread().isInterrupted()) {
            result = true;
            this.lock = true;
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        boolean result = false;
        long timeToEnd = System.currentTimeMillis() + unit.toMillis(time);
        while (this.lock && timeToEnd - System.currentTimeMillis() >= 0 && !Thread.currentThread().isInterrupted()) {
            Thread.sleep(UNIVERSAL_TIMER);
        }
        if (!this.lock) {
            result = true;
            lock();
        }
        return result;
    }

    @Override
    public void unlock() {
        if (this.lock && !Thread.currentThread().isInterrupted()) {
            this.lock = false;
            semaphore.release();
        }
    }

    @Override
    public Condition newCondition() {
        throw new UnsupportedOperationException("Expected error. Run method newCondition.");
    }
}