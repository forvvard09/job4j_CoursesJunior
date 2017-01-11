package ru.spoddubnyak.start;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests class StubInput.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 06.01.2016
 */
public class StubInputTest {
    /**  an array responses.  */
    private  String[] answers = {"1", "2", "3", "4", "5"};

    /**
     * .
     */
    @Test
    public void whenAddTwoNewItemsThenGetSameResultInTracker() {
        StubInput stubInput = new StubInput(answers);
        for (String str : answers) {
            String expectedRresponse = stubInput.ask("Testing question: ");
            assertThat(str, is(expectedRresponse));
        }
    }
}
