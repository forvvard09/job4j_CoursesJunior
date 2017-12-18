package ru.spoddubnyak;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BubleArrayTest {

    @Test
    public void whenSortArrayBuble() {
        BubleArray array = new BubleArray(new int[]{4, 7, 4, 0, 3, 1, 6, 9});
        assertThat(array.sortBuble(), is(new int[]{0, 1, 3, 4, 4, 6, 7, 9}));
    }

    @Test
    public void whenCreationEmptyArray() {
        BubleArray array = new BubleArray(new int[]{});
        assertThat(array.sortBuble(), is(new int[]{}));
    }

    @Test
    public void whenNotSorting() {
        BubleArray array = new BubleArray(new int[]{1, 2, 3, 4, 5, 6, 7});
        assertThat(array.sortBuble(), is(new int[]{1, 2, 3, 4, 5, 6, 7}));
    }
}