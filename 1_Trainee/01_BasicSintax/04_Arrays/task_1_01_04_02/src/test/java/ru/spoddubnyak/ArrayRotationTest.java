package ru.spoddubnyak;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArrayRotationTest {

    ArrayRotation sourceArray = new ArrayRotation(new int[][]{
            {1, 1, 1, 1, 1},
            {2, 2, 2, 2, 2},
            {3, 3, 3, 3, 3},
            {4, 4, 4, 4, 4},
            {5, 5, 5, 5, 5},
    });

    @Test
    public void whenTurnArrayRight() {
        int[][] checkedArray = {
                {5, 4, 3, 2, 1},
                {5, 4, 3, 2, 1},
                {5, 4, 3, 2, 1},
                {5, 4, 3, 2, 1},
                {5, 4, 3, 2, 1}
        };

        assertThat(sourceArray.turnArrayRight(), is(checkedArray));
    }

    @Test
    public void whenTurnArrayLeft() {
        int[][] checkedArray = {
                {1, 2, 3, 4, 5},
                {1, 2, 3, 4, 5},
                {1, 2, 3, 4, 5},
                {1, 2, 3, 4, 5},
                {1, 2, 3, 4, 5},
        };
        assertThat(sourceArray.turnArrayLeft(), is(checkedArray));
    }
}
