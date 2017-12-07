package ru.spoddubnyak.start;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests class StubInput.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 2.0
 * @since 16.02.2017
 */
public class StubInputTest {

    /**  an array responses.  */
    private  String[] answers = {"1", "2", "3", "4", "5"};

    /**
     * Test. Emulation of keyboard input data.
     */
    @Test
    public void whenExpectedInputThenGetExpectedAnswer() {
        StubInput stubInput = new StubInput(answers);
        for (String str : answers) {
            String expectedRresponse = stubInput.ask("Testing question: ");
            assertThat(str, is(expectedRresponse));
        }
    }
}
