package ru.spoddubnyak;

import org.junit.Test;
import ru.spoddubnyak.parsers.DomParsing;
import ru.spoddubnyak.parsers.SaxExample;
import ru.spoddubnyak.parsers.StaxExample;

import java.io.FileNotFoundException;

/**
 * Perfomance test for methods parsing.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 06.02.2018
 */
public class PerfomanceTestParsingMethod {
    /**
     * property for start time  test's.
     */
    private long startTime;
    /**
     * property formating to print.
     */
    private String newLine = System.getProperty("line.separator");

    /**
     * Perfomance test for method parsing DOM.
     *
     * @throws FileNotFoundException errors with xml file
     */
    @Test
    public void perfomanceTestexampleDom() throws FileNotFoundException {
        final String methodParsing = "DOM";
        startTime = System.currentTimeMillis();
        DomParsing dom = new DomParsing();
        dom.toStart();
        System.out.printf("%s===%s===%s%s%s ms%s", newLine, methodParsing, newLine, "Total time: ",
                (System.currentTimeMillis() - startTime), newLine);
    }

    /**
     * Perfomance test for method parsing Sax.
     *
     * @throws FileNotFoundException errors with xml file
     */
    @Test
    public void perfomanceTestexampleSax() throws FileNotFoundException {
        final String methodParsing = "Sax";
        startTime = System.currentTimeMillis();
        SaxExample sax = new SaxExample();
        sax.toStart();
        System.out.printf("%s===%s===%s%s%s ms%s", newLine, methodParsing, newLine, "Total time: ",
                (System.currentTimeMillis() - startTime), newLine);
    }

    /**
     * Perfomance test for method parsing Stax.
     *
     * @throws FileNotFoundException errors with xml file
     */
    @Test
    public void perfomanceTestexampleStax() throws FileNotFoundException {
        final String methodParsing = "Stax";
        startTime = System.currentTimeMillis();
        StaxExample stax = new StaxExample();
        stax.toStart();
        System.out.printf("%s===%s===%s%s%s ms%s", newLine, methodParsing, newLine, "Total time: ",
                (System.currentTimeMillis() - startTime), newLine);
    }
}