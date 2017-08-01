package ru.spoddubnyak;

import org.junit.Before;
import org.junit.Test;
import ru.spoddubnyak.errors.NotElementsInCollectionsSet;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Tests class  SimpleSetLinledList.
 *
 * @param <E> This describes my type parameter
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 29.05.2017
 */
public class SimpleSetLinkedListTest<E> {

    /**
     * property array for a test data set.
     */
    private Integer[] testDataSet;

    /**
     * Method generation test data.
     *
     */
    @Before
    public void generationTestData() {
        final int testOne = 1;
        final int testTwo = 2;
        final int testThree = 3;
        final int testFour = 4;
        final int testFive = 5;
        final int testSix = 6;
        final int testSeven = 7;
        this.testDataSet = new Integer[]{testOne, testTwo, testThree, testFour, testFive, testSix, testSeven};
    }
    /**
     * Test method add(E e), getElement(i index), getSizeSet().
     */
    @Test
    public void whenAddNewElementThenGetNewElement() {
        SimpleSetLinkedList<E> set = new SimpleSetLinkedList<E>();
        set.add((E) this.testDataSet[0]);
        final int expectedSize = 1;
        assertThat(set.getSizeSet(), is(expectedSize));
        assertThat(set.getElementValue(set.getElement(0)), is(this.testDataSet[0]));
    }

    /**
     * Test method add(E e), attempt to add multiple duplicates.
     */
    @Test
    public void whenAddDublicatesThenGetExpectedResultNotDublicates() {
        SimpleSetLinkedList<E> set = new SimpleSetLinkedList<E>();
        set.add((E) this.testDataSet[0]);
        set.add((E) this.testDataSet[0]);
        set.add((E) this.testDataSet[1]);
        set.add((E) this.testDataSet[0]);
        final int expectedSize = 2;
        assertThat(set.getSizeSet(), is(expectedSize));
        assertThat(set.getElementValue(set.getElement(0)), is(this.testDataSet[0]));
        assertThat(set.getElementValue(set.getElement(1)), is(this.testDataSet[1]));
    }

    /**
     * Test methods add(E e), remove (E e) and empty().
     */
    @Test
    public void whenCheckEmptySetThenGetExpectedResult() {
        SimpleSetLinkedList<E> set = new SimpleSetLinkedList<E>();
        assertThat(set.isEmpty(), is(false));
        set.add((E) this.testDataSet[0]);
        assertThat(set.isEmpty(), is(true));
        set.remove((E) this.testDataSet[0]);
        assertThat(set.isEmpty(), is(false));
    }

    /**
     * Test methods add(E e), remove (E e) and empty().
     */
    @Test
    public void whenRemoveNonExistenElementSetThenGetResultFalse() {
        SimpleSetLinkedList<E> set = new SimpleSetLinkedList<E>();
        set.add((E) this.testDataSet[0]);
        assertThat(set.remove((E) this.testDataSet[1]), is(false));
    }

    /**
     * Test methods remove (E e) and empty().
     */
    @Test(expected = NotElementsInCollectionsSet.class)
    public void whenRemoveElementEmptySetThenGetErrorNotElementsInCollectionsSet() {
        SimpleSetLinkedList<E> set = new SimpleSetLinkedList<E>();
        assertThat(set.remove((E) this.testDataSet[0]), is(false));
    }

    /**
     * Test methods add(E e), remove (E e) and empty().
     */
    @Test
    public void whenIterarorThenGetExpectedResult() {
        SimpleSetLinkedList<Integer> set = new SimpleSetLinkedList<Integer>();
        int expectedElementOne = 0;
        set.add(this.testDataSet[expectedElementOne++]);
        set.add(this.testDataSet[expectedElementOne++]);
        set.add(this.testDataSet[expectedElementOne++]);
        set.add(this.testDataSet[expectedElementOne++]);
        set.add(this.testDataSet[expectedElementOne++]);
        set.add(this.testDataSet[expectedElementOne++]);
        set.add(this.testDataSet[expectedElementOne]);
        Iterator<Integer> setIterator = set.iterator();
        Integer[] expectedResult = new Integer[set.getSizeSet()];
        int i = 0;
        while (setIterator.hasNext()) {
            expectedResult[i++] = setIterator.next();
        }
        for (int j = 0; j < set.getSizeSet(); j++) {
            assertThat(set.getElementValue(set.getElement(j)), is(this.testDataSet[j]));
        }
        assertThat(set.getSizeSet(), is(this.testDataSet.length));
    }
}
