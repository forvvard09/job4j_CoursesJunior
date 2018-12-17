package ru.spoddubnyak;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;


/**
 * CountingOfFunctions - calculate linear, quadric and log functions with help lambdas.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 10.12.2018
 */
public class CountingOfFunctions {
    /**
     * property -  constant value for calculate ODZ.
     */
    private static final double ZERO_FOR_ODZ = 0.0;
    /**
     * property -  constant value for calculate ODZ.
     */
    private static final double ONE_FOR_ODZ = 1.0;
    /**
     * property -  for save results.
     */
    private List<Double> result;

    /**
     * Constructor of new object CountingOfFunctions by specification.
     */
    public CountingOfFunctions() {
        this.result = new ArrayList<>();
    }

    /**
     * Method calculation function on diaposon.
     *
     * @param start start number to diaposone
     * @param end   start number to diaposone
     * @param func  lambda, which calculate result of functons
     * @return list results calculate
     */
    public List<Double> diapason(final int start, final int end, final Function<Integer, Double> func) {
        for (int i = start; i <= end; ++i) {
            this.result.add(func.apply(i));
        }
        return this.result;
    }

    /**
     * Method calculation linear function on diaposon.
     *
     * @param start start number to diaposone
     * @param end start number to diaposone
     * @param k coefficient of linear function
     * @param m coefficient of linear function
     *
     * @return list results calculate
     */
    public List<Double> calculateLinearFunction(final int start, final int end, final double k, final double m) {
        this.diapason(start, end, x -> k * x + m);
        return this.result;
    }

    /**
     * Method calculation quadric function on diaposon.
     *
     * @param start start number to diaposone
     * @param end   start number to diaposone
     * @param a     coefficient of linear function
     * @param b     coefficient of linear function
     * @param c     coefficient of linear function
     * @return list results calculate
     * @throws OdzException error ODZ
     */
    public List<Double> calculateQuadricFunction(final int start, final int end, final double a, final double b, final double c) throws OdzException {
        final double powNumber = 2.0;
        if (!checkEquality(a, CountingOfFunctions.ZERO_FOR_ODZ)) {
            this.diapason(start, end, x -> a * degreeNumber(x, powNumber) + b * x + c);
        } else {
            throw new OdzException("Error ODZ.");
        }
        return this.result;
    }

    /**
     * Method calculation logarithmic function on diaposon.
     *
     * @param start start number to diaposone
     * @param end   start number to diaposone
     * @param a     coefficient of linear function
     * @param m     coefficient of linear function
     * @return list results calculate
     * @throws OdzException error ODZ
     */
    public List<Double> calculateLogarithmicFunction(final int start, final int end, final double a, final double m) throws OdzException {
        if (!checkEquality(a, CountingOfFunctions.ONE_FOR_ODZ) && checkMore(a, CountingOfFunctions.ZERO_FOR_ODZ)) {
            this.diapason(start, end, x -> calculateLog(a, m));
        } else {
            throw new OdzException("Error ODZ");
        }
        return this.result;
    }

    /**
     * Auxiliary method for check ODZ.
     *
     * @param checkNumber    number for checking
     * @param expectedNumber number is checking
     * @return result result of comparing two numbers
     */
    private boolean checkEquality(double checkNumber, double expectedNumber) {
        Predicate<Double> isChecked = value -> value == expectedNumber;
        return isChecked.test(checkNumber);
    }

    /**
     * Auxiliary method for check ODZ.
     *
     * @param checkNumber    number for checking
     * @param expectedNumber number is more checking
     * @return result result of comparing two numbers
     */
    private boolean checkMore(double checkNumber, double expectedNumber) {
        Predicate<Double> isChecked = value -> value > expectedNumber;
        return isChecked.test(checkNumber);
    }

    /**
     * Auxiliary method raising a number to a power.
     *
     * @param value number for exponentiation
     * @param pow   degree for erection
     * @return result result number to a power
     */
    private double degreeNumber(double value, double pow) {
        BinaryOperator<Double> degree = (valueOne, valueTwo) -> Math.pow(valueOne, valueTwo);
        return degree.apply(value, pow);
    }

    /**
     * Auxiliary method calculate log for number on the basis.
     *
     * @param a basis
     * @param x number
     * @return result result log number on the basis
     */
    private double calculateLog(double a, double x) {
        UnaryOperator<Double> log = valueOne -> Math.log(valueOne);
        return log.apply(a) / log.apply(x);
    }
}
