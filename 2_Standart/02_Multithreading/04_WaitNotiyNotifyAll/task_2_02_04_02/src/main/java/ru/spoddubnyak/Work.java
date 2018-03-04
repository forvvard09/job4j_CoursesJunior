package ru.spoddubnyak;

/**
 * Class Work embody work for thread pool.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 03.03.2018
 */
public class Work {
    /**
     * property for timer sleeping.
     */
    private final int timer;
    /**
     * property for save value for start count.
     */
    private int startValue;
    /**
     * property for save value for finish count .
     */
    private int finishValue;

    /**
     * Constructor it creates a new object Work with the specified.
     *
     * @param startValue  for property startValue of object Work
     * @param finishValue for property finishValue of object Work
     * @param timer       for property timer of object Work
     */
    public Work(final int startValue, final int finishValue, final int timer) {
        this.startValue = startValue;
        this.finishValue = finishValue;
        this.timer = timer;
    }

    /**
     * Method embody work.
     *
     * @return result of work
     */
    public int doWork() {
        int result = this.startValue;
        while (result < this.finishValue) {
            result += result;
            sleep(this.timer);
        }
        return result;
    }

    /**
     * Method embody delay.
     *
     * @param timer for delay
     */
    private void sleep(int timer) {
        try {
            Thread.sleep((long) timer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}