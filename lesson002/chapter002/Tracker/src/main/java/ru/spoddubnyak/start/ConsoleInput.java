package ru.spoddubnyak.start;

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
     * @param question - question in console
     * @return - answer
     */
    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

  /*  @Override
    public int ask(String question, int[] range) {
        int key = Integer.valueOf(this.ask(question));
        boolean exist = false;
        for (int value : range) {
           if (value == key) {
               exist = true;
               break;
           }
        }
        return exist ? key : -1;*/

}