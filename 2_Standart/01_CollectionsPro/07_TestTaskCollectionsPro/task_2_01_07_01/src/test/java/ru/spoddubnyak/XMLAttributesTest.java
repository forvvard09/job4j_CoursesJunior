package ru.spoddubnyak;

import org.junit.Test;
import ru.spoddubnyak.model.XMLAttributes;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Tests class XMLAttributes.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 08.02.2018
 */
public class XMLAttributesTest {

    /**
     * Property array with expected result.
     */
    private final String[] expectedResults = {"book", "AddOrder", "DeleteOrder", "operation",
            "price", "volume", "orderId", "BUY", "SELL"};

    /**
     * Test testing method getAttributes and get value class's XMLAttributes.
     */
    @Test
    public void whenExpectedResultValueThenGetResultValue() {
        for (int i = 0; i < XMLAttributes.values().length; i++) {
            assertThat(XMLAttributes.values()[i].getAtributes(), is(expectedResults[i]));
        }
    }
}
