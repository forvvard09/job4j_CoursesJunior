package ru.spoddubnyak;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.core.Is.is;

/**
 * Class test class IteratorPrimeNumber.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 27.04.2017
 */
public class IteratorPrimeNumberTest {

    /**
     * Test class IteratorEvenNumber, last elements not prime number.
     */
    @Test
    public void whenLastElementArrayNotPrimeNumberThenGetExpectedResult() {
        final int[] arrayValue = {1, 2, 3, 5, 4, 9, 7, 12, 14};
        final Integer[] expectedResult = {2, 3, 5, 7};
        IteratorPrimeNumber it = new IteratorPrimeNumber(arrayValue);
        List<Integer> result = new ArrayList();
        while (it.hasNext()) {
            result.add((Integer) it.next());
        }
        assertThat(result, containsInAnyOrder(expectedResult));
    }

    /**
     * Test class IteratorEvenNumber, last elements prime number.
     */
    @Test
    public void whenLastElementArrayPrimeNumberThenGetExpectedResult() {
        final int[] arrayValue = {1, 2, 3, 5, 4, 9, 25, 14, 7, 11};
        final Integer[] expectedResult = {2, 3, 5, 7, 11};
        IteratorPrimeNumber it = new IteratorPrimeNumber(arrayValue);
        List<Integer> result = new ArrayList();
        while (it.hasNext()) {
            result.add((Integer) it.next());
        }
        assertThat(result, containsInAnyOrder(expectedResult));
    }

    /**
     * Test class IteratorEvenNumber, last elements prime number.
     */
    @Test
    public void whenAllElementsArrayPrimeNumberThenGetExpectedResult() {
        final int[] arrayValue = {2, 3, 5, 7, 11};
        final Integer[] expectedResult = {2, 3, 5, 7, 11};
        IteratorPrimeNumber it = new IteratorPrimeNumber(arrayValue);
        List<Integer> result = new ArrayList();
        while (it.hasNext()) {
            result.add((Integer) it.next());
        }
        assertThat(result, containsInAnyOrder(expectedResult));
    }

    /**
     * Test class IteratorEvenNumber, not prime number.
     */
    @Test
    public void whenNotPrimeNumberThenGetHashNextFalse() {
        final int[] arrayValue = {1, 4, 6, 9, 12};
        IteratorPrimeNumber it = new IteratorPrimeNumber(arrayValue);
        assertThat(it.hasNext(), is(false));
    }

}