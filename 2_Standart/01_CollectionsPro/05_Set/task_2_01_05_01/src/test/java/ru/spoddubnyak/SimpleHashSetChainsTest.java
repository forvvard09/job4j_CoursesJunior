package ru.spoddubnyak;

import org.junit.Before;
import org.junit.Test;
import ru.spoddubnyak.hashSet.SimpleHashSetChains;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Tests class  SimpleHashSetChains.
 *
 * @param <E> This describes my type parameter
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 07.08.2017
 */
public class SimpleHashSetChainsTest<E> {
    /**
     * property array for a test data set.
     */
    private String[] testDataSet;
    /**
     * property for a test data set.
     */
    private String errorData = "error";

    /**
     * Method generation test data.
     */
    @Before
    public void generationTestData() {
        final String testOne = "testOne";
        final String testTwo = "testTwo";
        final String testThree = "testThree";
        final String testFour = "testFour";
        final String testFive = "testFive";
        final String testSix = "testSix";
        final String testSeven = "testSeven";
        this.testDataSet = new String[]{testOne, testTwo, testThree, testFour, testFive, testSix, testSeven};
    }

    /**
     * Test method add(E e), find(E e), getSizeSet().
     */
    @Test
    public void whenAddNewElementThenGetNewElement() {
        SimpleHashSetChains<E> set = new SimpleHashSetChains<E>();
        set.add((E) this.testDataSet[0]);
        final int expectedSize = 1;
        assertThat(set.getSizeSet(), is(expectedSize));
        assertThat(set.existenceValid((E) this.testDataSet[0]), is(true));
        assertThat(set.existenceValid((E) this.testDataSet[1]), is(false));
    }

    /**
     * Test method add(E e), attempt to add multiple duplicates.
     */
    @Test
    public void whenAddDublicatesThenGetExpectedResultNotDublicates() {
        SimpleHashSetChains<String> set = new SimpleHashSetChains<String>();
        set.add(this.testDataSet[0]);
        set.add(this.testDataSet[0]);
        set.add(this.testDataSet[1]);
        set.add(this.testDataSet[0]);
        final int expectedSize = 2;
        assertThat(set.getSizeSet(), is(expectedSize));
        assertThat(set.existenceValid(this.testDataSet[0]), is(true));
        assertThat(set.existenceValid(this.testDataSet[1]), is(true));
        assertThat(set.existenceValid(this.testDataSet[2]), is(false));
    }

    /**
     * Test methods add(E e) and automatic HashTable increase.
     */
    @Test
    public void whenThenGetOne() {
        SimpleHashSetChains<String> set = new SimpleHashSetChains<String>(0);
        int expectedIndex = 0;
        int expectedSizeHashTableOne = set.getSizeHashTable();
        set.add(this.testDataSet[expectedIndex++]);
        set.add(this.testDataSet[expectedIndex++]);
        set.add(this.testDataSet[expectedIndex++]);
        set.add(this.testDataSet[expectedIndex++]);
        set.add(this.testDataSet[expectedIndex++]);
        set.add(this.testDataSet[expectedIndex++]);
        int expectedSizeHashTableTwo = set.getSizeHashTable();
        set.add(this.testDataSet[expectedIndex]);
        final int expectedSize = 7;
        expectedIndex = 0;
        assertThat(expectedSizeHashTableOne < expectedSizeHashTableTwo, is(true));
        assertThat(set.getSizeSet(), is(expectedSize));
        assertThat(set.existenceValid(this.testDataSet[expectedIndex++]), is(true));
        assertThat(set.existenceValid(this.testDataSet[expectedIndex++]), is(true));
        assertThat(set.existenceValid(this.testDataSet[expectedIndex++]), is(true));
        assertThat(set.existenceValid(this.testDataSet[expectedIndex++]), is(true));
        assertThat(set.existenceValid(this.testDataSet[expectedIndex++]), is(true));
        assertThat(set.existenceValid(this.testDataSet[expectedIndex++]), is(true));
        assertThat(set.existenceValid(this.testDataSet[expectedIndex]), is(true));
        assertThat(set.existenceValid(this.errorData), is(false));
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
        SimpleHashSetChains<String> set = new SimpleHashSetChains<String>();
        for (String s : testArray) {
            set.add(s);
        }
        for (String s : testArray) {
            set.add(s);
        }
        assertThat(set.getSizeSet(), is(lengthTestArr));
    }
}
