package ru.spoddubnyak;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Test class - testing class StructureDivisions.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 03.10.2017
 */
public class StructureDivisionsTest {
    /**
     * property array for a test data set.
     */
    private String[] testData;
    /**
     * property for a test data set.
     */
    private String[] expectedResult;

    /**
     * Method generation test data.
     */
    @Before
    public void generationInputTestData() {
        this.testData = new String[]{
                "K1/SK1",
                "K1/SK2",
                "K1/SK1/SSK1",
                "K1/SK1/SSK2",
                "K2",
                "K2/SK1/SSK1",
                "K2/SK1/SSK2"};
    }

    /**
     * Method generation expected result.
     */
    @Before
    public void generationExpectedResultsortToFinish() {
        this.expectedResult = new String[]{
                "K1",
                "K1/SK1",
                "K1/SK1/SSK1",
                "K1/SK1/SSK2",
                "K1/SK2",
                "K2",
                "K2/SK1",
                "K2/SK1/SSK1",
                "K2/SK1/SSK2"};
    }

    /**
     * Method generation expected result.
     */
    @Before
    public void generationExpectedResultsortToStart() {
        this.expectedResult = new String[]{
                "K2",
                "K2/SK1",
                "K2/SK1/SSK2",
                "K2/SK1/SSK1",
                "K1",
                "K1/SK2",
                "K1/SK1",
                "K1/SK1/SSK2",
                "K1/SK1/SSK1"};
    }

    /**
     * Test sorting ascending.
     */
    @Test
    public void whenSortingAscendingThenGetExpectedResult() {
        generationInputTestData();
        generationExpectedResultsortToFinish();
        StructureDivisions divisions = new StructureDivisions(testData);
        divisions.sorting(true);
        assertThat(expectedResult, is(divisions.sorting(true).toArray()));
    }

    /**
     * Test sorting decresing order.
     */
    @Test
    public void whenSortingDecredingOrderThenGetExpectedResult() {
        generationInputTestData();
        generationExpectedResultsortToStart();
        StructureDivisions divisions = new StructureDivisions(testData);
        divisions.sorting(true);
        assertThat(expectedResult, is(divisions.sorting(false).toArray()));
    }
}