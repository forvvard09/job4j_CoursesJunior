package ru.spoddubnyak;

/**
 * Class SimpleLock implement interface Lock.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 2.0
 * @since 27.03.2018
 */

public class SimpleLock {

    /**
     * property NAME_LOCK_THREAD - for set name thread when block lock .
     */
    private static final String NAME_LOCK_THREAD = "thredIsLock";

    private Thread thread;

    /**
     * property lock - object free flag.
     */
    private boolean isLock;

    /**
     * Constructor it creates a new object ThreadPool with the specified.
     */
    public SimpleLock() {
        this.isLock = false;
    }

    /**
     * Gettter for property lock, return state of object, false - object is free, true - isn't free.
     *
     * @return state of object, false - object is free, true - isn't free
     */
    public boolean getIsLock() {
        return this.isLock;
    }

    /**
     * Method does the lock check free,  if yes - grabs, otherwise blocked.
     */
    public void lock() {
        synchronized (this) {
            while (this.isLock) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.isLock = true;
            this.thread = Thread.currentThread();
        }
    }

    /**
     * Method does the thread know if it is loky, if so, it frees.
     */
    public void unlock() {
        synchronized (this) {
            if (this.isLock && Thread.currentThread() == this.thread) {
                this.isLock = false;
                this.notifyAll();
                this.thread = null;
            }

        }
    }
}