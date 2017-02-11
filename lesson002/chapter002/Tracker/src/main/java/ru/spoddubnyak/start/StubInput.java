package ru.spoddubnyak.start;

import ru.spoddubnyak.errors.MenuOutException;

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
     *
     * @param answers - the array of possible answers
     *
     */
    public StubInput(String[] answers) {
        String[] copyAnswers = Arrays.copyOf(answers, answers.length);
        this.answers = copyAnswers;
    }

    @Override
    public String ask(String question) {
        return this.answers[position++];
    }

    @Override
    public int ask(String question, int[] range) {
        String answer = this.answers[position++];
        String newLine = System.getProperty("line.separator");
        if (answer.equals("q")) {
            System.out.printf("%s%s%s%s%s%s", "=>", newLine, "Completion of the work program.", newLine, "-----", newLine);
            System.exit(0);
        }
        int key = ((Integer.valueOf(answer)) - 1);
        boolean exist = false;
        for (int value : range) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        if (exist) {
            return key;
        } else {
            throw new MenuOutException("Out of menu range.");
        }
    }


    @Override
    public long ask(String question, long maxNumber) {
        String inConsole = this.answers[position++];
        long answer = Long.parseLong(inConsole);
        return answer;
    }


}
