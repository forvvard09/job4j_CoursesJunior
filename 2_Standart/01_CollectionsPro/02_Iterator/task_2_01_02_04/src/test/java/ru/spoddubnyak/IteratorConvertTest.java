package ru.spoddubnyak;

import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
/**
 * Class test class IteratorConvert.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 05.05.2017
 */
public class IteratorConvertTest {
    /**
     * property arrOne - arrays for filling testing data.
     */
    private final Integer[] arrOne = {4, 2, 0, 4, 6, 4, 9};
    /**
     * property arrOne - arrays for filling testing data.
     */
    private final Integer[] arrTwo = {0, 9, 8, 7, 5};
    /**
     * property arrOne - arrays for filling testing data.
     */
    private final Integer[] arrThree = {1, 3, 5, 6, 7, 0, 9, 8, 4};
    /**
     * property expectedResult - list for expected result.
     */
    private List<Integer> expectedResult = new ArrayList<>();
    /**
     * new Object class IteratorConvert.
     */
    private IteratorConvert converter = new IteratorConvert();

    /**
     * Test method convert, IteratorConvert.
     */
    @Test
    public void whenExpectedResultThenGetResultOne() {
        final Iterator<Iterator<Integer>> iteratorIterator = Arrays.asList(Arrays.asList(arrOne).iterator(), Arrays.asList(arrTwo).iterator(), Arrays.asList(arrThree).iterator()).iterator();
        fillingExpectedResult(this.arrOne, this.arrTwo, this.arrThree);
        final Iterator<Integer> iterator = converter.convert(iteratorIterator);
        final List<Integer> result = new ArrayList<>();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        assertThat(result, is(expectedResult));
    }

    /**
     * Test method convert, IteratorConvert.
     */
    @Test
    public void whenExpectedResultThenGetResultTwo() {
        final Iterator<Iterator<Integer>> iteratorIterator = Arrays.asList(Arrays.asList(arrOne).iterator(), Arrays.asList(arrOne).iterator(), Arrays.asList(arrOne).iterator()).iterator();
        fillingExpectedResult(this.arrOne, this.arrOne, this.arrOne);
        final Iterator<Integer> iterator = converter.convert(iteratorIterator);
        final List<Integer> result = new ArrayList<>();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        assertThat(result, is(expectedResult));
    }

    /**
     * Test method convert, Incorrect input get error NoSuchElementException.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenIncorrectInputResultThenGetError() {
        final Iterator<Iterator<Integer>> iteratorIterator = Arrays.asList(Arrays.asList(new Integer[] {}).iterator(), Arrays.asList(new Integer[] {}).iterator(), Arrays.asList(new Integer[] {}).iterator()).iterator();
        final Iterator<Integer> iterator = converter.convert(iteratorIterator);
        final List<Integer> result = new ArrayList<>();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
    }

    /**
     * Auxiliary method for filling out the expected result.
     * @param arrayOne - property this.arrOne
     * @param arrayTwo - property this.arrTwo
     * @param arrayThree - property this.arrThree
     */
    private void fillingExpectedResult(Integer[] arrayOne, Integer[] arrayTwo, Integer[] arrayThree) {
        for (Integer i : arrayOne) {
            this.expectedResult.add(i);
        }
        for (Integer i : arrayTwo) {
            this.expectedResult.add(i);
        }
        for (Integer i : arrayThree) {
            this.expectedResult.add(i);
        }
    }
}