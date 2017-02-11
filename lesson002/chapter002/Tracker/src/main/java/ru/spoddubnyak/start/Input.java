package ru.spoddubnyak.start;

/**
 * Interface class to communicate via the console.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 04.01.2017
 */
public interface Input {

    /** Method ask and get an answer to a question.
     * @param question - question in console
     * @return - answer
     */
    String ask(String question);


    /** Method ask and get an answer to a question and check with possible answers.
     * @param question - question in console
     * @param range - range of possible responses
     * @return - answer
     */
    int ask(String question, int[] range);

    /**
     * Method ask and get an answer to a question, checks no longer whether the resulting number is the maximum possible.
     *
     * @param question  - question in console
     * @param maxNumber - the maximum possible
     * @return - answer
     */
    long ask(String question, long maxNumber);

}
