package ru.spoddubnyak;

import ru.spoddubnyak.commandString.CommandLineArgs;
import ru.spoddubnyak.commandString.InputParametrs;

public class Init {

    public static void main(String[] args) {

        InputParametrs parametrs = new CommandLineArgs().parsingArgs(args);
        System.out.println(parametrs);

    }

}
