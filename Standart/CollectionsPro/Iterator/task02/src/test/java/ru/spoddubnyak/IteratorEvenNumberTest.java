package ru.spoddubnyak;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.core.Is.is;

/**
 * Class test class IteratorMultiArray.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 27.04.2017
 */
public class IteratorEvenNumberTest {

    /**
     * Test class IteratorEvenNumber, last elements not even number.
     */
    @Test
    public void whenLastElementArrayNotEvenNumberThenGetExpectedResult() {
        final int[] arrayValue = {1, 3, 4, 5, 6, 7, 8, 9, 9};
        final Integer[] expectedResult = {4, 6, 8};
        IteratorEvenNumber it = new IteratorEvenNumber(arrayValue);
        List<Integer> result = new ArrayList();
        while (it.hasNext()) {
            result.add((Integer) it.next());
        }
        assertThat(result, containsInAnyOrder(expectedResult));
    }


    /**
     * Test class IteratorEvenNumber, last elements even number.
     */
    @Test
    public void whenLastElementArrayEvenNumberThenGetExpectedResult() {
        final int[] arrayValue = {1, 3, 4, 5, 6, 7, 8, 9, 9, 10};
        final Integer[] expectedResult = {4, 6, 8, 10};
        IteratorEvenNumber it = new IteratorEvenNumber(arrayValue);
        List<Integer> result = new ArrayList();
        while (it.hasNext()) {
            result.add((Integer) it.next());
        }
        assertThat(result, containsInAnyOrder(expectedResult));
    }

    /**
     * Test class IteratorEvenNumber, all elements even number.
     */
    @Test
    public void whenAllElementsArrayEvenNumberThenGetExpectedResult() {
        final int[] arrayValue = {2, 10, 4, 6, 8, 10};
        final Integer[] expectedResult = {2, 10, 4, 6, 8, 10};
        IteratorEvenNumber it = new IteratorEvenNumber(arrayValue);
        List<Integer> result = new ArrayList();
        while (it.hasNext()) {
            result.add((Integer) it.next());
        }
        assertThat(result, containsInAnyOrder(expectedResult));
    }

    /**
     * Test class IteratorEvenNumber, array not contain even number.
     */
    @Test
    public void whenArrayNotEvenNumberThenGetExceptionNotevenNumber() {
        final int[] arrayValue = {1, 3, 5, 7, 9};
        IteratorEvenNumber it = new IteratorEvenNumber(arrayValue);
        assertThat(it.hasNext(), is(false));
    }
}
