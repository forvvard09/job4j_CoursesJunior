package ru.spoddunyak.models;

import org.junit.Test;
import ru.spoddubnyak.errors.NoFindValueException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Tests class SimpleArray.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 10.05.2017
 */
public class SimpleArrayTest {

    /**
     * Test constructor create new object SimpleArray to specification.
     *
     * @throws NoFindValueException if findValue not find in array
     */
    @Test
    public void whenCreateObjectByConstructorGetLengthArrayInitial() throws NoFindValueException {
        final int expectedLengthArr = 4;
        SimpleArray<Integer> simpleArray = new SimpleArray<>();
        assertThat(expectedLengthArr, is(simpleArray.getLengthArr()));
    }

    /**
     * Test method add and method get.
     *
     * @throws NoFindValueException if findValue not find in array
     */
    @Test
    public void whenAddTypeStringGetExpectedResult() throws NoFindValueException {
        final String expectedResult = "test";
        SimpleArray<String> simpleArray = new SimpleArray<>();
        simpleArray.add(expectedResult);
        assertThat(expectedResult, is(simpleArray.get(0)));
    }

    /**
     * Test method update element.
     *
     * @throws NoFindValueException if findValue not find in array
     */
    @Test
    public void whenUpadteIntegerElementGetExpectedResult() throws NoFindValueException {
        final Integer lastElement = 33;
        final Integer updateElement = 99;
        SimpleArray<Integer> simpleArray = new SimpleArray<>();
        simpleArray.add(lastElement);
        simpleArray.update(lastElement, updateElement);
        assertThat(updateElement, is(simpleArray.get(0)));
    }

    /**
     * Test error not find element.
     *
     * @throws NoFindValueException if findValue not find in array
     */
    @Test (expected = NoFindValueException.class)
    public void whenUpadteIntegerNotElementGetNoFindValueException() throws NoFindValueException {
        final Integer lastElement = 33;
        final Integer updateElement = 99;
        final Integer testElement = 100;
        SimpleArray<Integer> simpleArray = new SimpleArray<>();
        simpleArray.add(lastElement);
        simpleArray.update(testElement, updateElement);
    }

    /**
     * Test remove not first and not last element by array.
     *
     * @throws NoFindValueException if findValue not find in array
     */
    @Test
    public void whenRemoveElementGetExpectedResponse() throws NoFindValueException {
        final Integer firstElement = 1;
        final Integer secondElement = 2;
        final Integer thirdElement = 3;
        final Integer fourElement = 4;
        SimpleArray<Integer> simpleArray = new SimpleArray<>();
        simpleArray.add(firstElement);
        simpleArray.add(secondElement);
        simpleArray.add(thirdElement);
        simpleArray.add(fourElement);
        simpleArray.delete(secondElement);
        assertThat(thirdElement, is(simpleArray.get(1)));
        assertThat(fourElement, is(simpleArray.get(2)));
    }

    /**
     * Test remove first element by array.
     *
     * @throws NoFindValueException if findValue not find in array
     */
    @Test
    public void whenRemoveFirstElementGetExpectedResponse() throws NoFindValueException {
        final Integer firstElement = 1;
        final Integer secondElement = 2;
        final Integer thirdElement = 3;
        final Integer fourElement = 4;
        SimpleArray<Integer> simpleArray = new SimpleArray<>();
        simpleArray.add(firstElement);
        simpleArray.add(secondElement);
        simpleArray.add(thirdElement);
        simpleArray.add(fourElement);
        simpleArray.delete(firstElement);
        assertThat(secondElement, is(simpleArray.get(0)));
        assertThat(fourElement, is(simpleArray.get(2)));
    }

    /**
     * Test remove first element by array.
     *
     * @throws NoFindValueException if findValue not find in array
     */
    @Test
    public void whenRemoveLastElementGetExpectedResponse() throws NoFindValueException {
        final int expectedResult = 3;
        final Integer firstElement = 1;
        final Integer secondElement = 2;
        final Integer thirdElement = 3;
        final Integer fourElement = 4;
        SimpleArray<Integer> simpleArray = new SimpleArray<>();
        simpleArray.add(firstElement);
        simpleArray.add(secondElement);
        simpleArray.add(thirdElement);
        simpleArray.add(fourElement);
        simpleArray.delete(fourElement);
        assertThat(expectedResult, is(simpleArray.getLengthArr()));
        assertThat(thirdElement, is(simpleArray.get(2)));
    }
}