package ru.spoddubnyak.start;

import java.util.Arrays;

/**
 * Class emulation of the user experience.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 05.01.2017
 */
public class StubInput implements Input {
    /**
     * property - the array of possible answers.
     */
    private String[] answers;
    /**
     * property - position answer.
     */
    private int position = 0;

    /** Constructor it creates a new object with the specified values.
     * @param answers - the array of possible answers
     */
    public StubInput(String[] answers) {
        String[] copyAnswers = Arrays.copyOf(answers, answers.length);
        this.answers = copyAnswers;
    }

    @Override
    public String ask(String question) {
        return this.answers[position++];
    }
}
