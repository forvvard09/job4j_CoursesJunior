package ru.spoddubnyak;

/**
 * Tests class MainStream - class run thread time.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 09.01.2018
 */
public class MainStream {
    /**
     * property -  start time.
     */
    private long startTime;
    /**
     * property -  duration work.
     */
    private long durationWork;
    /**
     * property -  input text line for worker thread.
     */
    private String textLineForwork;
    /**
     * property -  timer for testing.
     */
    private long timerForTesting;

    /**
     * Constructor - create new object, by specificated.
     *
     * @param startTime       field by specification
     * @param durationWork    field by specification
     * @param textLineForwork field by specification
     * @param timerForTesting field by specification
     */
    public MainStream(final long startTime, final long durationWork, final String textLineForwork, final long timerForTesting) {
        this.startTime = startTime;
        this.durationWork = durationWork;
        this.textLineForwork = textLineForwork;
        this.timerForTesting = timerForTesting;
    }

    /**
     * Method run thread Time.
     *
     * @return thread with result work
     * @throws InterruptedException - for problems threads
     */
    public Thread initMainStrim() throws InterruptedException {
        Thread threadWork = new Thread(new CountChar(this.textLineForwork, timerForTesting));
        Thread threadTime = new Thread(new Time(this.startTime, this.durationWork, threadWork));
        threadTime.start();
        return threadTime;
    }
}
