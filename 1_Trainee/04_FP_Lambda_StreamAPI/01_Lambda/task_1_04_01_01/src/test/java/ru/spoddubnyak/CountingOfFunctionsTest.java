package ru.spoddubnyak;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Tests class SimpleArray.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 10.12.2018
 */
public class CountingOfFunctionsTest {
    /**
     * property -  List for save expected result.
     */
    private List<Double> expectedResult = new ArrayList<>();
    /**
     * property -  object for testing.
     */
    private CountingOfFunctions countingOfFunctions = new CountingOfFunctions();
    /**
     * property -  Auxiliary array for testing.
     */
    private Double[] arrExpected;
    /**
     * property -  List for save expected result.
     */
    private List<Double> result;

    /**
     * property -  const value for odz.
     */
    private static final int START_DIAPOSONE = 1;
    /**
     * property -  const value for odz.
     */
    private static final int END_DIAPOSONE = 5;


    /**
     * Test method calculateLinearFunction().
     */
    @Test
    public void whenCalculateLinearFunctionGetExpectedResult() {
        final double expectedResult1 = 12.0;
        final double expectedResult2 = 17.0;
        final double expectedResult3 = 22.0;
        final double expectedResult4 = 27.0;
        final double expectedResult5 = 32.0;
        this.arrExpected = new Double[]{expectedResult1, expectedResult2, expectedResult3, expectedResult4, expectedResult5};
        this.expectedResult = Arrays.asList(this.arrExpected);
        final double koef1 = 5.0;
        final double koef2 = 7.0;
        result = countingOfFunctions.calculateLinearFunction(START_DIAPOSONE, END_DIAPOSONE, koef1, koef2);
        assertThat(this.expectedResult, is(this.result));
    }

    /**
     * Test method calculateQuadricFunction() get expected result.
     *
     * @throws OdzException error ODZ
     */
    @Test
    public void whenCalculateQuadricFunctionGetExpectedResult() throws OdzException {
        final double expectedResult1 = 9.0;
        final double expectedResult2 = 18.0;
        final double expectedResult3 = 31.0;
        final double expectedResult4 = 48.0;
        final double expectedResult5 = 69.0;
        //this.arrExpected = new Double[]{expectedResult1, expectedResult2, expectedResult3, expectedResult4, expectedResult5};
        //this.expectedResult = Arrays.asList(this.arrExpected);
        Collections.addAll(this.expectedResult, expectedResult1, expectedResult2, expectedResult3, expectedResult4, expectedResult5);
        final double koef1 = 2.0;
        final double koef2 = 3.0;
        final double koef3 = 4.0;
        result = countingOfFunctions.calculateQuadricFunction(START_DIAPOSONE, END_DIAPOSONE, koef1, koef2, koef3);
        assertThat(this.expectedResult, is(this.result));
    }

    /**
     * Test method calculateQuadricFunction(), get exception.
     *
     * @throws OdzException error ODZ
     */
    @Test(expected = OdzException.class)
    public void whenCalculateQuadricFunctionGetErrorODZ() throws OdzException {
        final double koef1 = 0.0;
        final double koef2 = 3.0;
        final double koef3 = 4.0;
        result = countingOfFunctions.calculateQuadricFunction(START_DIAPOSONE, END_DIAPOSONE, koef1, koef2, koef3);
    }

    /**
     * Test method calculateLogarithmicFunction(), get exception.
     *
     * @throws OdzException error ODZ
     */
    @Test(expected = OdzException.class)
    public void whenCalculateLogarithmicFunctionGetErrorODZ() throws OdzException {
        final double koef1 = 0.0;
        final double koef2 = -3.0;
        result = countingOfFunctions.calculateLogarithmicFunction(START_DIAPOSONE, END_DIAPOSONE, koef1, -koef2);
    }

    /**
     * Test method calculateLogarithmicFunction(), get expected result.
     *
     * @throws OdzException error ODZ
     */
    @Test(expected = OdzException.class)
    public void whenCalculateLogarithmicFunctionGetExpectedResult() throws OdzException {
        final double koef1 = 0.0;
        final double koef2 = -3.0;
        result = countingOfFunctions.calculateLogarithmicFunction(START_DIAPOSONE, END_DIAPOSONE, koef1, -koef2);
    }
}
