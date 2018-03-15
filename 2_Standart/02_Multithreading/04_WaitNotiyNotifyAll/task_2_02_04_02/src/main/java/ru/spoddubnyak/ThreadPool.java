package ru.spoddubnyak;


import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Class ThreadPool embody thread pool.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 2.0
 * @since 14.03.2018
 */
@ThreadSafe
public class ThreadPool implements PoolWork {
    /**
     * property for save count processors.
     */
    private static final int COUNT_PROC = Runtime.getRuntime().availableProcessors();
    /**
     * property embody queue for run work.
     */
    @GuardedBy("itself")
    private final Queue<Work> queuePool;
    /**
     * property storage for threads.
     */
    private List<Thread> threadsList;
    /**
     * property save ressult -amount all works.
     */
    private volatile int sumAllWorks;

    /**
     * Constructor it creates a new object ThreadPool with the specified.
     */
    public ThreadPool() {
        this.queuePool = new ArrayDeque<>();
        this.threadsList = new ArrayList(COUNT_PROC);
        this.sumAllWorks = 0;
    }

    /**
     * Getter for property queuePool.
     *
     * @return link property queuePool
     */
    public Queue<Work> getQueuePool() {
        return this.queuePool;
    }

    /**
     * Getter for property threadsList.
     *
     * @return link property threadsList
     */
    public List<Thread> getThreadsList() {
        return this.threadsList;
    }

    /**
     * Getter for property COUNT_PROC.
     *
     * @return link property COUNT_PROC
     */
    public int getCountProc() {
        return this.COUNT_PROC;
    }

    /**
     * Getter for property sumAllWorks.
     *
     * @return link property sumAllWorks
     */
    public int getSumAllWorks() {
        return this.sumAllWorks;
    }

    /**
     * Setter for property sumAllWorks.
     *
     * @param sumAllWorks value for recods to proprty sumAllWorks of object ThreadPool
     */
    public void setSumAllWorks(int sumAllWorks) {
        this.sumAllWorks = sumAllWorks;
    }

    @Override
    public void add(Work work) {
        synchronized (this.queuePool) {
            this.queuePool.offer(work);
            this.queuePool.notify();
        }
    }

    /**
     * Method return object Work from start queue.
     *
     * @return object Work from start queue
     */
    public Work getWorkOutQueue() {
        Work work;
        synchronized (this.queuePool) {
            work = this.queuePool.poll();
        }
        return work;
    }

    /**
     * Method add new thread to collections and start thread.
     */
    public void startThreadPool() {
        for (int i = 0; i < this.getCountProc(); i++) {
            this.threadsList.add(new Thread(new ThreadDoWork()));
            this.threadsList.get(i).start();
        }
    }

    /**
     * Method stop threads and clear threadList.
     */
    public void stopThreadPool() {
        for (Thread thread : this.threadsList) {
            thread.interrupt();
        }
        this.threadsList.clear();
    }

    /**
     * Inner class ThreadDoWork embody threadsList fr run work.
     */
    class ThreadDoWork implements Runnable {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                synchronized (queuePool) {
                    while (queuePool.isEmpty()) {
                        System.out.printf("%s%s", "Queue is empty. Wait for queue filling.", System.getProperty("line.separator"));
                        try {
                            queuePool.wait();
                        } catch (InterruptedException ignore) {
                            System.out.printf("%s%s", "Thread is stop.", System.getProperty("line.separator"));
                            return;
                        }
                    }
                    Work work = getWorkOutQueue();
                    setSumAllWorks(getSumAllWorks() + work.doWork());
                }
            }
        }
    }
}