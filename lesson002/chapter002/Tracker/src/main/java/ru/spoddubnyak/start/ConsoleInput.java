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
     * property - Sysstem object Scanner for make in console.
     */
    private Scanner scanner = new Scanner(System.in);

    @Override
    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

}
