package ru.spoddubnyak;


import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests class Context.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 16.01.2017
 */
public class ContextTest {


    /**
     * property - side length.
     */
    private final int side = 3;
    /**
     * property - new line.
     */
    private String newLine = System.getProperty("line.separator");
    /**
     * property - new object Context for testings.
     */
    private Context context = new Context(new Square());

    /**
     * Test method formating and display shape Square.
     */
    @Test
    public void whenSetSideSquareThenGetSquare() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        context.executeStrategy(side);
        String expectedSphere = String.format("%s%s%s%s%s%s%s", "[][][]", newLine, "[][][]", newLine, "[][][]", newLine, newLine);
        assertThat(out.toString(), is(expectedSphere));
    }

    /**
     * Test method formating and display shape Triangle.
     */
    @Test
    public void whenSetSideTriangleThenGetTriangle() {
        context = new Context(new Triangle());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        context.executeStrategy(side);
        String expectedSphere = String.format("%s%s%s%s%s%s%s", "   *", newLine, "  ***", newLine, " *****", newLine, newLine);
        assertThat(out.toString(), is(expectedSphere));
    }
}
