package ru.spoddubnyak;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CountingOfFunctions {
    public List<Double> diapason(int start, int end, Function<Integer, Double> func) {
        List<Double> list = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            list.add(func.apply(i));
        }
        return list;
    }

    public List<Double> linearFunc(int startInput, int endInput, double k, double m) {
        return this.diapason(startInput, endInput, x -> k * x + m );

    }



}
