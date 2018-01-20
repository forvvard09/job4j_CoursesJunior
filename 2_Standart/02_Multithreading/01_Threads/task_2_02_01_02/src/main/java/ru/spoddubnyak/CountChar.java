package ru.spoddubnyak;

/**
 * Class CountChar - counting count simvols in text line.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 10.01.2018
 */
public class CountChar implements Runnable {
    /**
     * property -  text line for count testing.
     */
    private final String textLineForWork;
    /**
     * property -  timer for testing.
     */
    private final long timerForTesting;

    /**
     * Constructor - create new object, create for two threads for work of classes CountSymbolsInLine.
     *
     * @param textLineForWork field by specification
     * @param timerForTesting field by specification
     */
    public CountChar(String textLineForWork, long timerForTesting) {
        this.textLineForWork = textLineForWork;
        this.timerForTesting = timerForTesting;
    }

    @Override
    public void run() {
        char[] textToChar = this.textLineForWork.toCharArray();
        int countSymbols = 0;
        boolean finishTime = false;

        try {
            Thread.sleep(this.timerForTesting);
            for (int i = 0; i < textToChar.length; i++) {
                if (Thread.currentThread().isInterrupted()) {
                    finishTime = true;
                    break;
                }
                countSymbols++;
            }
            if (!finishTime) {
                System.out.printf("%s = %s.", "Count symbols", countSymbols);
            }
        } catch (InterruptedException ignore) { /*NOP*/ }
    }
}

