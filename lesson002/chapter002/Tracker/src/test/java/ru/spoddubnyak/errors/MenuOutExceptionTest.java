package ru.spoddubnyak.errors;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test Class MenuOutException.
 *
 * @author @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 11.02.2017
 */
public class MenuOutExceptionTest extends RuntimeException {

    /**
     * Test class MenuOutException.
     */
    @Test
    public void whenRunErrorThenGetErrorMessage() {
        new MenuOutException("ErrorTesting.");
        assertThat("ErrorTesting.", is("ErrorTesting."));
    }
}