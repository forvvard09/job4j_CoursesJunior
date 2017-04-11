package ru.spoddubnyak;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class сonverts a two-dimensional array into a collection of List back.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 03.04.2017
 */
public class ConvertListTest {
    /**
     * property - testList, collection List for tests.
     */
    private static final List<Integer> TEST_LIST = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);


    /**
     * Test method convert two-dimensional array into a collection List.
     */
    @Test
    public void whenConvertTwoDimensionalArrayThenGetList() {
        final int[][] testArray = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}, {16, 17, 18, 19, 20}};
        final List<Integer> expectedList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        assertThat(expectedList, is(new ConvertList().toList(testArray)));
    }

    /**
     * Test One method convert a collection List to two-dimensional by Iterator.
     */
    @Test
    public void whenConvertListByIteratorThenGetTwoDimensionalArrayOne() {
        final int[][] expectedArray = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}};
        final int ROWS = 2;
        assertThat(expectedArray, is(new ConvertList().toArrayByIterator(TEST_LIST, ROWS)));
    }


    /**
     * Test Two method convert a collection List to two-dimensional by Iterator.
     */
    @Test
    public void whenConvertListByIteratorThenGetTwoDimensionalArrayTwo() {
        final int[][] expectedArray = {{1, 2}, {3, 4}, {5, 6}, {7, 8}, {9, 10}, {0, 0}};
        final int ROWS = 6;
        assertThat(expectedArray, is(new ConvertList().toArrayByIterator(TEST_LIST, ROWS)));
    }

    /**
     * Test Two method convert a collection List to two-dimensional by Iterator.
     */
    @Test
    public void whenConvertListByIteratorThenGetTwoDimensionalArrayThree() {
        final int[][] expectedArray = {{1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}, {9}, {10}, {0}, {0}, {0}, {0}, {0}};
        final int ROWS = 15;
        assertThat(expectedArray, is(new ConvertList().toArrayByIterator(TEST_LIST, ROWS)));
    }

    /**
     * Test One method convert List<int[]> to List<Integer>,  Different array sizes.
     */
    @Test
    public void whenDifferentSizesArraysThenExpectedCollections() {
        List list = new ArrayList<int[]>();
        final int[] oneArray = new int[]{1, 2, 3, 4};
        final int[] twoArray = new int[]{5, 6, 7, 8};
        list.add(oneArray);
        list.add(twoArray);
        final List<Integer> expectedList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        assertThat(expectedList, is(new ConvertList().convert(list)));
    }

    /**
     * Test Two method convert List<int[]> to List<Integer>, Same array sizes.
     */
    @Test
    public void whenSametSizesArraysThenExpectedCollections() {
        List list = new ArrayList<int[]>();
        final int[] oneArray = new int[]{5, 6};
        final int[] twoArray = new int[]{1, 2, 3, 4, 4, 5};
        list.add(oneArray);
        list.add(twoArray);
        final List<Integer> expectedList = Arrays.asList(5, 6, 1, 2, 3, 4, 4, 5);
        assertThat(expectedList, is(new ConvertList().convert(list)));
    }
}