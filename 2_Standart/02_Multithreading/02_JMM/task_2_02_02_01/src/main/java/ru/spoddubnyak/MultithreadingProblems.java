package ru.spoddubnyak;

/**
 * Class MultithreadingProblems - shows the problem of multithreading.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 11.01.2018
 */
public class MultithreadingProblems {
    /**
     * property -  count steps for cycle.
     */
    private static final int COUNT_STEPS = 1_000_000_000;
    /**
     * property -  timer.
     */
    private static final int TIMER = 100;
    /**
     * property -  count resultVisibilityProblem.
     */
    private int resultVisibilityProblem = 0;
    /**
     * property -  count resultRaceCondition.
     */
    private volatile int resultRaceCondition = 0;

    /**
     * Getter for property count steps for cycle.
     *
     * @return value count steps for cycle
     */
    public int getCountStep() {
        return this.COUNT_STEPS;
    }

    /**
     * Getter for property count resultVisibilityProblem.
     *
     * @return value count resultVisibilityProblem
     */
    public int getResultVisibilityProblem() {
        return this.resultVisibilityProblem;
    }

    /**
     * Getter for property count resultRaceCondition.
     *
     * @return value count resultRaceCondition
     */
    public int getResultRaceCondition() {
        return this.resultRaceCondition;
    }

    /**
     * Method for sleep.
     *
     * @param time number milliseconds
     */
    private void sleeping(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method run threds and return count result.
     *
     * @throws InterruptedException - errors with threads
     */
    public void runing() throws InterruptedException {
        Thread threadOne = new Thread(new WorkerOne());
        WorkerTwo threadTwo = new WorkerTwo();
        threadOne.start();
        threadTwo.start();
        while (threadOne.isAlive() && threadTwo.isAlive()) {
            sleeping(TIMER);
        }
    }

    /**
     * Inner class WorkerOne, which create new Thread for count result.
     */
    class WorkerOne implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < COUNT_STEPS; i++) {
                resultVisibilityProblem++;
                resultRaceCondition++;
            }
        }
    }

    /**
     * Inner class WorkerTwo, which create new Thread for count result.
     */
    class WorkerTwo extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < COUNT_STEPS; i++) {
                resultVisibilityProblem++;
                resultRaceCondition++;
            }
        }
    }
}
