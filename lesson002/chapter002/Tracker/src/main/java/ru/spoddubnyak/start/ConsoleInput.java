package ru.spoddubnyak.start;

import ru.spoddubnyak.errors.MenuOutException;

import java.util.Scanner;

/**
 * Class to verify the ability to work through the console.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 04.01.2017
 */
public class ConsoleInput implements Input {


    /**
     * property - System object Scanner for make in console.
     */
    private Scanner scanner = new Scanner(System.in, "UTF-8");

    /**
     * Method ask and get an answer to a question.
     *
     * @param question question in console
     * @return answer
     */
    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

    /**
     * Method ask and get an answer to a question, checks no longer whether the resulting number is the maximum possible.
     *
     * @param question  question in console
     * @param maxNumber the maximum possible
     * @return answer
     */
    public long ask(String question, long maxNumber) {
        System.out.print(question);
        String inConsole = scanner.nextLine();
        long answer = Long.parseLong(inConsole);
        return answer;
    }

    /**
     * Method ask and get an answer to a question and check with possible answers.
     *
     * @param question question in console
     * @param range    possible answers
     * @return answer
     */
    public int ask(String question, int[] range) {
        String answer = this.ask(question);
        int key = Integer.parseInt(answer) - 1;
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
}