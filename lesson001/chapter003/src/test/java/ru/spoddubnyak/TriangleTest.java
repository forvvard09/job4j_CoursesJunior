package ru.spoddubnyak;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TriangleTest {

    // Error area triangle
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    // calculation area triangle
    @Test
    public void whenSideTriangleThenCalculationArea() {
        Triangle triangle = new Triangle(new Point(1D, 0D), new Point(-2D, 4D), new Point(-2D, -2D));
        assertThat(triangle.area(), is(closeTo(9D, 0.001)));
    }

    @Test
    public void whenSideTriangleThenError() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("It is impossible to calculate the area of a triangle.");
        Triangle triangle = new Triangle(new Point(1, 1), new Point(1, 1), new Point(1, 1));
        assertThat(triangle.area(), is("It is impossible to calculate the area of a triangle."));
    }
}