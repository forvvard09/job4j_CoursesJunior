package ru.spoddubnyak;

/**
 * + * The class CountSymbolsInLine counting spaces and words in String line, use 2 other Thread.
 * + *
 * + * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * + * @version 1.0
 * + * @since 07.12.2017
 * +
 */
public class CountSymbolsInLine implements Runnable {
    /**
     * property -  input String line for describes count spaces and count words.
     */
    private final String line;

    /**
     * Constructor - creates a new object CountSymbolsInLine by specififcation.
     *
     * @param line inputstring line
     */
    public CountSymbolsInLine(String line) {
        this.line = line;
    }

    /**
     * Getter for property line.
     *
     * @return value property line
     */
    public String getLine() {
        return this.line;
    }

    /**
     * Method return object Thread which count space in string line.
     *
     * @return value Thread
     */
    public Thread getCountSpace() {
        return new Thread(new CountSymbolsInLine(this.line));
    }

    /**
     * Method return object Thread which count words in string line.
     *
     * @return value property line
     */
    public Thread getCountWords() {
        return new CountWordsNewThread();
    }

    @Override
    public void run() {
        char[] textToChar = this.line.toCharArray();
        int count = 0;
        for (char ch : textToChar) {
            if (ch == ' ') {
                count++;
            }
        }
        System.out.printf("%s. %s: %s.%s", "Thread number 2", "Count space in line", count, System.getProperty("line.separator"));
    }

    /**
     * Inner class CountWordsNewThread, which create new Thread for count words in string line.
     */
    class CountWordsNewThread extends Thread {
        @Override
        public void run() {
            int count = 1;
            String workedLine = getLine().trim();
            char[] textToChar = workedLine.toCharArray();
            for (int i = 0; i < workedLine.length() - 1; i++) {
                if (textToChar[i] == ' ' && textToChar[i + 1] != ' ') {
                    count++;
                }
            }
            System.out.printf("%s. %s: %s.%s", "Thread number 1", "Count words in line", count, System.getProperty("line.separator"));
        }
    }
}