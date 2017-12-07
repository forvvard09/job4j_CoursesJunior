package ru.spoddubnyak;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PointTest {

    // method distanceTo
    @Test
    public void whenDistanceToThenCalculatingSideTraingle() {
        Point pointOne = new Point(1D, 3D);
        Point pointTwo = new Point(2D, 3D);
        assertThat(pointOne.distanceTo(pointTwo), is(1D));
    }
}