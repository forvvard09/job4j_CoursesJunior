package ru.spoddubnyak;

import org.junit.Before;
import org.junit.Test;
import ru.spoddubnyak.hashSet.SimpleHashSetChains;
import ru.spoddubnyak.hashSet.SimpleHashSetOpenMethod;

/**
 * Test class adding entries from collections is required for further performance testing Collections: SimpleSetArray, SimpleSetLinledList, .
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 17.08.2017
 */
public class PerfomanceSetCollections {
    /**
     * property array for a test data set.
     */
    private String[] testDataSet;

    /**
     * property - additional property, new line.
     */
    private static String newLine = System.getProperty("line.separator");

    /**
     * property - number of testing entries.
     */
    private final int lengthArrForTesting = 100000;

    /**
     * property - testing entry.
     */
    private String testRecord = "testRecording";

    /**
     * property - startTime for to determine the initial measurement time.
     */
    private long startTime;

    /**
     * Method generation test data.
     */
    @Before
    public void generationTestData() {
        this.testDataSet = new String[lengthArrForTesting];
        for (int j = 0; j < testDataSet.length; j++) {
            testDataSet[j] = String.format("%s - %s", testRecord, j);
        }
    }

    /**
     * Auxiliary method calculate the time to add entries to the collection SimpleSetArray.
     * @return - time taken to add entries
     */
    private long perfomanceSimpleSetArray() {
        generationTestData();
        SimpleSetArray<String> set = new SimpleSetArray<String>(lengthArrForTesting);
        startTime = System.currentTimeMillis();
        for (String s : this.testDataSet) {
            set.add(s);
        }
        return System.currentTimeMillis() - startTime;
    }

    /**
     * Auxiliary method calculate the time to add entries to the collection SimpleSetLinledList.
     * @return - time taken to add entries
     */
    private long perfomanceSimpleSetLinledList() {
        generationTestData();
        SimpleSetLinkedList<String> set = new SimpleSetLinkedList<String>();
        startTime = System.currentTimeMillis();
        for (String s : this.testDataSet) {
            set.add(s);
        }
        return System.currentTimeMillis() - startTime;
    }

    /**
     * Auxiliary method calculate the time to add entries to the collection SimpleHashSetChains.
     * @return - time taken to add entries
     */
    private long perfomanceSimpleHashSetChains() {
        generationTestData();
        SimpleHashSetChains<String> set = new SimpleHashSetChains<String>();
        startTime = System.currentTimeMillis();
        for (String s : this.testDataSet) {
            set.add(s);
        }
        return System.currentTimeMillis() - startTime;
    }

    /**
     * Auxiliary method calculate the time to add entries to the collection SimpleHashSetOpenMethod.
     * @return - time taken to add entries
     */
    private long perfomanceSimpleHashSetOpenMethod() {
        generationTestData();
        SimpleHashSetOpenMethod<String> set = new SimpleHashSetOpenMethod<String>();
        startTime = System.currentTimeMillis();
        for (String s : this.testDataSet) {
            set.add(s);
        }
        return System.currentTimeMillis() - startTime;
    }

    /**
     * Perfomance test for method add, for collection SimpleSetArray.
     */
    @Test
    public void  whenUsedCollectionSimpleSetArrayAddThenGetExpectedResult() {
        System.out.printf("%s %s %s: %s %s%s", "Collection SimpleSetArray add", this.lengthArrForTesting, "entries", perfomanceSimpleSetArray(), "ms;", newLine);
    }

    /**
     * Perfomance test for method add, for collection SimpleSetLinkedLis.
     */
    @Test
    public void whenUsedCollectionSimpleSetLinkedListGetExpectedResult() {
        System.out.printf("%s %s %s: %s %s%s", "Collection SimpleSetLinkedList add", this.lengthArrForTesting, "entries", perfomanceSimpleSetLinledList(), "ms;", newLine);
    }

    /**
     * Perfomance test for method add, for collection SimpleHashSetChains.
     */
    @Test
    public void whenUsedCollectionSimpleHashSetChainsGetExpectedResult() {
        System.out.printf("%s %s %s: %s %s%s", "Collection SimpleHashSetChains add", this.lengthArrForTesting, "entries", perfomanceSimpleHashSetChains(), "ms;", newLine);
    }

    /**
     * Perfomance test for method add, for collection SimpleHashSetOpenMethod.
     */
    @Test
    public void whenUsedCollectionSimpleHashSetOpenMethodGetExpectedResult() {
        System.out.printf("%s %s %s: %s %s%s", "Collection SimpleHashSetOpenMethod add", this.lengthArrForTesting, "entries", perfomanceSimpleHashSetOpenMethod(), "ms;", newLine);
    }
}
