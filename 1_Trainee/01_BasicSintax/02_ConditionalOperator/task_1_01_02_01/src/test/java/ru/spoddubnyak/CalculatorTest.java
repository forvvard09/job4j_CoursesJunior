package ru.spoddubnyak;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CalculatorTest {

    // method add
    @Test
    public void whenAddNumberThenResultPositiveNumber() {
        Calculator calc = new Calculator();
        calc.add(5D, 3D);
        assertThat(calc.getResult(), is(8D));
    }

    @Test
    public void whenAddNumberThenResultNegativeNumber() {
        Calculator calc = new Calculator();
        calc.add(-9D, 3D);
        assertThat(calc.getResult(), is(-6D));
    }

    @Test
    public void whenAddNumberThenResultZero() {
        Calculator calc = new Calculator();
        calc.add(-3D, 3D);
        assertThat(calc.getResult(), is(0D));
    }

    //method subtruct
    @Test
    public void whenSubtructNumberThenResultPositiveNumber() {
        Calculator calc = new Calculator();
        calc.subtruct(15D, 2D);
        assertThat(calc.getResult(), is(13D));
    }

    @Test
    public void whenSubtructNumberThenResultNegativeNumber() {
        Calculator calc = new Calculator();
        calc.subtruct(-9D, 3D);
        assertThat(calc.getResult(), is(-12D));
    }

    @Test
    public void whenSubtructNumberThenResultZero() {
        Calculator calc = new Calculator();
        calc.subtruct(3D, 3D);
        assertThat(calc.getResult(), is(0D));
    }

    //method div
    @Test
    public void whenDivNumberThenResultPositiveNumber() {
        Calculator calc = new Calculator();
        calc.div(15D, 3D);
        assertThat(calc.getResult(), is(5D));
    }

    @Test
    public void whenDivNumberThenResultNegativeNumber() {
        Calculator calc = new Calculator();
        calc.div(-9D, 3D);
        assertThat(calc.getResult(), is(-3D));
    }

    //method multiple
    @Test
    public void whenMultipleNumberThenResultPositiveNumber() {
        Calculator calc = new Calculator();
        calc.multiple(2.5D, 2D);
        assertThat(calc.getResult(), is(5D));
    }

    @Test
    public void whenMultipleNumberThenResultNegativeNumber() {
        Calculator calc = new Calculator();
        calc.multiple(-9D, 3D);
        assertThat(calc.getResult(), is(-27D));
    }

    @Test
    public void whenMultipleNumberThenResultZero() {
        Calculator calc = new Calculator();
        calc.multiple(3D, 0D);
        assertThat(calc.getResult(), is(0D));
    }

}