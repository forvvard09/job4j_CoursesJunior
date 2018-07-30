package ru.spoddubnyak.commandString;


import org.apache.commons.cli.*;

public class CommandLineArgs {

    private Option optionNumberFloors;
    private Option optionHeightFloor;
    private Option optionElevatorSpeed;
    private Option optionTimeMoveDoor;
    private InputParametrs parametrs;


    private Options options;


    public CommandLineArgs() {
        this.options = new Options();
    }

    public Options getOptions() {
        return this.options;
    }

    private Option createOptionNumberFloors() {
        this.optionNumberFloors = new Option("n", "number floors", true, "number floors");
        this.optionNumberFloors.setArgs(1);
        this.optionNumberFloors.setArgName("number floors");
        return this.optionNumberFloors;
    }

    private Option createOptionHeightFloor() {
        this.optionHeightFloor = new Option("h", "height of floor", true, "height of floor");
        this.optionHeightFloor.setArgs(1);
        this.optionHeightFloor.setArgName("height of floor");
        return this.optionHeightFloor;
    }

    private Option createOptionElevatorSpeed() {
        this.optionElevatorSpeed = new Option("s", "elevator speed", true, "elevator speed");
        this.optionElevatorSpeed.setArgs(1);
        this.optionElevatorSpeed.setArgName("elevator speed");
        return this.optionElevatorSpeed;
    }

    private Option createOptionTimeMoveDoor() {
        this.optionTimeMoveDoor = new Option("t", "time move door", true, "time move door");
        this.optionTimeMoveDoor.setArgs(1);
        this.optionTimeMoveDoor.setArgName("time move door");
        return this.optionTimeMoveDoor;
    }

    private Options addingParametrsToOptions() {
        this.options.addOption(createOptionNumberFloors());
        this.options.addOption(createOptionHeightFloor());
        this.options.addOption(createOptionElevatorSpeed());
        this.options.addOption(createOptionTimeMoveDoor());
        return this.options;
    }

    public InputParametrs parsingArgs(String[] args) {
        final CommandLineArgs cmd = new CommandLineArgs();
        final InputParametrs inputParametrs;
        final int minFloor = 5;
        final int maxFloor = 20;

        CommandLineParser parser = new DefaultParser();
        try {
            // parse the command line arguments
            int numberFloors = 0;
            double hightFloor = 0;
            double elevatorSpeed = 0;
            int timeMoveDoor = 0;

            CommandLine line = parser.parse(cmd.addingParametrsToOptions(), args);
            if (line.hasOption("n")) {
                numberFloors = Integer.parseInt(line.getOptionValue("n"));
            }
            if (line.hasOption("h")) {
                hightFloor = Double.parseDouble((line.getOptionValue("h")));
            }
            if (line.hasOption("s")) {
                elevatorSpeed = Double.parseDouble((line.getOptionValue("s")));
            }
            if (line.hasOption("t")) {
                timeMoveDoor = Integer.parseInt(line.getOptionValue("t"));
            }

            if (numberFloors < minFloor && minFloor > maxFloor) {
                System.out.println("Тут будет вызов ошибки....по этажам");
            }
            if (numberFloors == 0 && hightFloor == 0 && elevatorSpeed == 0 && timeMoveDoor == 0) {
                System.out.println("Тут будет ошибка про ввод данных, один из параметров 0");

            }
            this.parametrs = new InputParametrs(numberFloors, hightFloor, elevatorSpeed, timeMoveDoor);

        } catch (ParseException exp) {
            // oops, something went wrong
            System.err.println("Parsing failed.  Reason: " + exp.getMessage());
        }
        return this.parametrs;
    }
}