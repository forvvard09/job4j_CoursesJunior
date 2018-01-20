package ru.spoddubnyak;

/**
 * Class ShowMessagesForThreads - send information message start program, and finish program.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 18.12.2017
 */
public class ShowMessagesForThreads {
    /**
     * property -  COUNT_WORKER worker Thread.
     */
    private static final int COUNT_WORKER = 2;

    /**
     * property -  array Thread for work.
     */
    private Thread[] threads;

    /**
     * Constructor - create new object, create for two threads for work of classes CountSymbolsInLine.
     *
     * @param textLine text line for worker threads
     */
    public ShowMessagesForThreads(final String textLine) {
        threads = new Thread[this.COUNT_WORKER];
        CountSymbolsInLine threadsCountSymbol = new CountSymbolsInLine(textLine);
        threads[0] = threadsCountSymbol.getCountSpace();
        threads[1] = threadsCountSymbol.getCountWords();
    }

    /**
     * Method out display text message.
     *
     * @param textMesage - text message for out display
     */
    private void showMessage(final String textMesage) {
        System.out.printf("%s%s", textMesage, System.getProperty("line.separator"));
    }

    /**
     * Method create new Thread, which runs 2 threads for work.
     *
     * @return Thread which runs 2 threads for work
     * @throws InterruptedException - errors thread
     */
    public Thread init() throws InterruptedException {
        return new Thread() {
            @Override
            public void run() {
                showMessage("Start program.");
                threads[0].start();
                threads[1].start();
                try {
                    threads[1].join();
                    threads[0].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                showMessage("Finish program.");
            }
        };
    }
}
