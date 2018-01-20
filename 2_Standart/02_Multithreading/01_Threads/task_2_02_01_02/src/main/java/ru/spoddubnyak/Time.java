package ru.spoddubnyak;

/**
 * Class Time - counting count symbols in text line.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 10.01.2018
 */
public class Time implements Runnable {
    /**
     * property -  timer for wait run steps.
     */
    private static final int DELAY = 100;
    /**
     * property -  timer compulsory finish thread.
     */
    private static final int COMPULSORY_FINISH_THREAD = 500;
    /**
     * property -  start time.
     */
    private long timeStart;
    /**
     * property -  duration work.
     */
    private long durationWork;
    /**
     * property -  thread for work, count symbols in text line.
     */
    private Thread workThread;

    /**
     * Constructor - create new object, create for two threads for work of classes CountSymbolsInLine.
     *
     * @param timeStart    field by specification
     * @param durationWork field by specification
     * @param workThread   field by specification
     */
    public Time(long timeStart, long durationWork, Thread workThread) {
        this.timeStart = timeStart;
        this.durationWork = durationWork;
        this.workThread = workThread;
    }

    @Override
    public void run() {
        this.workThread.start();
        boolean finishThread = false;
        boolean finishTime = false;
        long time = this.timeStart;
        while (!finishThread) {
            if (!this.workThread.isAlive()) {
                finishThread = true;
            }
            if (!finishThread && time >= (this.timeStart + this.durationWork)) {
                finishThread = true;
                finishTime = true;
                this.workThread.interrupt();
                try {
                    this.workThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;

            }
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time += DELAY;
        }
        try {
            Thread.sleep(COMPULSORY_FINISH_THREAD);
            if (this.workThread.isAlive()) {
                this.workThread.stop();
            }
        } catch (InterruptedException ignore) { /*NOP*/ }
        if (finishTime) {
            System.out.printf("%s.", "Finish time");
        }
    }
}
