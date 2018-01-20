package ru.spoddubnyak;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class test class IteratorMultiArray.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 24.04.2017
 */
public class IteratorMultiArrayTest {

    /**
     * Test class IteratorMultiArray values - matrix.
     */
    @Test
    public void whenArrayMatrixThenGetExpectedResult() {
        final int[][] arrayValue = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        final int[] expectedResult = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        IteratorMultiArray it = new IteratorMultiArray(arrayValue);
        final int lengthArr = 9;
        int[] result = new int[lengthArr];
        int count = 0;
        while (it.hasNext()) {
            result[count++] = (int) it.next();
        }
        assertThat(expectedResult, is(result));
    }

    /**
     * Test class IteratorMultiArray, values - multidimensional array.
     */
    @Test
    public void whenArrayThenGetExpectedResult() {
        final int[][] arrayValue = {{1, 2}, {3, 4, 5}, {6, 7, 8, 9}};
        final int[] expectedResult = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        IteratorMultiArray it = new IteratorMultiArray(arrayValue);
        final int lengthArr = 9;
        int[] result = new int[lengthArr];
        int count = 0;
        while (it.hasNext()) {
            result[count++] = (int) it.next();
        }
        assertThat(expectedResult, is(result));
    }

    /**
     * Test class IteratorMultiArray, not next element by hashNext false.
     */
    @Test
    public void whenNotNextElementThenGetHashNextFalse() {
        final int[][] arrayValue = {{1}};
        IteratorMultiArray it = new IteratorMultiArray(arrayValue);
        it.next();
        assertThat(it.hasNext(), is(false));
    }

    /**
     * Test class IteratorMultiArray,  next element by hashNext true.
     */
    @Test
    public void whenNextElementThenGetHashNextTrue() {
        final int[][] arrayValue = {{1}, {2}};
        IteratorMultiArray it = new IteratorMultiArray(arrayValue);
        it.next();
        assertThat(it.hasNext(), is(true));
    }
}