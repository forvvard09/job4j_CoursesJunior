package ru.spoddubnyak;

import org.junit.Before;
import org.junit.Test;
import ru.spoddubnyak.errors.NotElementsInCollectionsSet;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Tests class  SimpleSetArray.
 *
 * @param <E> This describes my type parameter
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 20.05.2017
 */
public class SimpleSetArrayTest<E> {
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
        SimpleSetArray<E> set = new SimpleSetArray<E>();
        set.add((E) this.testDataSet[0]);
        final int expectedSize = 1;
        assertThat(set.getSizeSet(), is(expectedSize));
        assertThat(set.getElement(0), is(this.testDataSet[0]));
    }

    /**
     * Test method add(E e), attempt to add multiple duplicates.
     */
    @Test
    public void whenAddDublicatesThenGetExpectedResultNotDublicates() {
        SimpleSetArray<E> set = new SimpleSetArray<E>();
        set.add((E) this.testDataSet[0]);
        set.add((E) this.testDataSet[0]);
        set.add((E) this.testDataSet[1]);
        set.add((E) this.testDataSet[0]);
        final int expectedSize = 2;
        assertThat(set.getSizeSet(), is(expectedSize));
        assertThat(set.getElement(0), is(this.testDataSet[0]));
        assertThat(set.getElement(1), is(this.testDataSet[1]));
    }

    /**
     * Test methods add(E e), remove (E e) and empty().
     */
    @Test
    public void whenCheckEmptySetThenGetExpectedResult() {
        SimpleSetArray<E> set = new SimpleSetArray<E>();
        assertThat(set.isEmpty(), is(false));
        set.add((E) this.testDataSet[0]);
        assertThat(set.isEmpty(), is(true));
        set.remove((E) this.testDataSet[0]);
        assertThat(set.isEmpty(), is(false));
    }

    /**
     * Test methods add(E e), remove (E e) and empty().
     */
    @Test(expected = NotElementsInCollectionsSet.class)
    public void whenRemoveElementEmptySetThenGetErrorNotElementsInCollectionsSet() {
        SimpleSetArray<E> set = new SimpleSetArray<E>();
        assertThat(set.remove((E) this.testDataSet[0]), is(false));
    }

    /**
     * Test methods add(E e), remove (E e) and empty().
     */
    @Test
    public void whenRemoveNonExistenElementSetThenGetResultFalse() {
        SimpleSetArray<E> set = new SimpleSetArray<E>();
        set.add((E) this.testDataSet[0]);
        assertThat(set.remove((E) this.testDataSet[1]), is(false));
    }

    /**
     * Test methods add(E e), remove (E e) and empty().
     */
    @Test
    public void whenIterarorThenGetExpectedResult() {
        SimpleSetArray<Integer> set = new SimpleSetArray<Integer>();
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
            assertThat(set.getElement(j), is(this.testDataSet[j]));
        }
        assertThat(set.getSizeSet(), is(this.testDataSet.length));
     }

    /**
     * Auxiliary method for data generation.
     * @param lengthTestArr number test entries
     * @return test array with a set of test data
     */
    private String[] generationTestDate(int lengthTestArr) {
        String[] testArray = new String[lengthTestArr];
        for (int i = 0; i < testArray.length; i++) {
            testArray[i] = String.format("%s - %s", "testRecords", i);
        }
        return testArray;
    }

    /**
     * Test method add(E e), attempt to add multiple duplicates for big data.
     */
    @Test
    public void whenAddDublicatesThenGetExpectedResultNotDublicatesBigData() {
        final int lengthTestArr = 5000;
        String[] testArray = generationTestDate(lengthTestArr);
        SimpleSetArray<String> set = new SimpleSetArray<String>(lengthTestArr);
        for (String s : testArray) {
            set.add(s);
        }
        for (String s : testArray) {
            set.add(s);
        }
        assertThat(set.getSizeSet(), is(lengthTestArr));
    }
}

