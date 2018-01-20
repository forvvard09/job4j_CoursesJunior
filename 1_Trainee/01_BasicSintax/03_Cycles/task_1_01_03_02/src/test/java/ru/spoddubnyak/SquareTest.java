package ru.spoddubnyak;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class SquareTest {

    @Test
    public void whenPositiveNumberThenCalculationSquare() {
        Square square = new Square(3, 6, 9);
        assertThat(square.calculate(2), is(33F));
    }

    @Test
    public void whenNegativeNumberThenCalculationSquare() {
        Square square = new Square(-1, -1, -1);
        assertThat(square.calculate(2), is(-7F));
    }
}