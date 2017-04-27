package ru.spoddubnyak;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

/**
 * Class test class IteratorMultiArray.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 24.04.2017
 */
public class IteratorMultiArrayTest {

    /**
     * Test class IteratorMultiArray.
     */
    @Test
    public void whenArrayMatrixThenGetExpectedResult() {
        final int[][] arrayValue = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        final int[] expectedResult = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        IteratorMultiArray it = new IteratorMultiArray(arrayValue);
        ArrayList listResult = new ArrayList(9);
        while (it.hasNext()) {
            listResult.add(it.hasNext());
        }
        assertThat(expectedResult, containsInAnyOrder(listResult));
    }
}