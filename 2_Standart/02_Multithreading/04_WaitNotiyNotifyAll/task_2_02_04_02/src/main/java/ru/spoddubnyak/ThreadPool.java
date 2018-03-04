package ru.spoddubnyak;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Semaphore;

/**
 * Class ThreadPool embody thread pool.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 01.03.2018
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
     * property for create ThreadGroup.
     */
    private ThreadGroup threadGroup = new ThreadGroup("ThreadPoolGroups");
    /**
     * property save ressult -amount all works.
     */
    private volatile int sumAllWorks;
    /**
     * property for to restrict access to a shared resource queuePool.
     */
    private Semaphore semaphore;

    /**
     * Constructor it creates a new object ThreadPool with the specified.
     */
    public ThreadPool() {
        this.queuePool = new ArrayDeque<>();
        this.semaphore = new Semaphore(COUNT_PROC);
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

    /**
     * Getter for property threadGroup.
     *
     * @return link property threadGroup
     */
    public ThreadGroup getThreadGroup() {
        return this.threadGroup;
    }

    @Override
    public void add(Work work) {
        synchronized (this.queuePool) {
            this.queuePool.offer(work);
        }
    }

    /**
     * Method create and start Threads in ThreadGroup.
     */
    public void startTreathGroup() {
        for (int i = 1; i <= COUNT_PROC; i++) {
            Thread thread = new Thread(this.threadGroup, new ThreadDoWork(), String.format("nameThread ,%s", i));
            thread.start();
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
     * Inner class ThreadDoWork embody threads fr run work.
     */
    class ThreadDoWork implements Runnable {
        @Override
        public void run() {
            while (threadGroup.activeCount() > 0) {
                synchronized (queuePool) {
                    while (queuePool.isEmpty()) {
                        System.out.printf("%s%s", "Queue is empty. Wait for queue filling.", "System.getProperty(\"line.separator\")");
                        try {
                            queuePool.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        semaphore.acquire();
                        Work work = getWorkOutQueue();
                        setSumAllWorks(getSumAllWorks() + work.doWork());
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}