package ru.spoddubnyak.commandString;

public class InputParametrs {
    private int numberFloors;
    private double heightFloor;
    private double elevatorSpeed;
    private int timeMoveDoor;


    public InputParametrs(int numberFloors, double heightFloor, double elevatorSpeed, int timeMoveDoor) {
        this.numberFloors = numberFloors;
        this.heightFloor = heightFloor;
        this.elevatorSpeed = elevatorSpeed;
        this.timeMoveDoor = timeMoveDoor;
    }

    public int getNumberFloors() {
        return this.numberFloors;
    }

    public double getHeightFloor() {
        return this.heightFloor;
    }

    public double getElevatorSpeed() {
        return this.elevatorSpeed;
    }

    public int getTimeMoveDoor() {
        return this.timeMoveDoor;
    }

    @Override
    public String toString() {
        return String.format("InputParametrs: numberFloors = %s, heightFloor = %s, elevatorSpeed = %s, timeMoveDoor = %s.",
                              this.numberFloors, this.heightFloor, this.elevatorSpeed, this.timeMoveDoor);
    }
}
