package ru.spoddubnyak;

public class Calculator {
    private double result;

    public void add(double first, double second) {
        this.result = first + second;
    }

    public void subtruct(double first, double second) {
        this.result = first - second;
    }

    public void div(double first, double second) {
        this.result = first / second;
    }

    public void multiple(double first, double second) {
        this.result = first * second;
    }

    public double getResult() {
        return this.result;
    }
}