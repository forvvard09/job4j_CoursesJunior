package ru.spoddubnyak;


import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test class adding and removing entries from collections is required for further performance testing Collections: LinkedList, ArrayList, TreeSet.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 02.04.2017
 */
public class PerfomanceCollectionsTest {
    /**
     * property - additional property, new line.
     */
    private static String newLine = System.getProperty("line.separator");
    /**
     * property - number of lines to add.
     */
    private final int numberAdd = 3000000;
    /**
     * property - number of lines to remove.
     */
    private final int numberRemove = 2500000;
    /**
     * property - template lines for add.
     */
    private final String templateLine = "Testing string for perfomance test";
    /**
     * property - startTime for to determine the initial measurement time.
     */
    private long resultTimeAdd;
    /**
     * property - startTime for to determine the initial measurement time.
     */
    private long resultTimeRemove;

    /**
     * Additional method for message by test.
     *
     * @param message - text for out console.
     */
    private static void getMessage(String message) {
        System.out.printf("%s%s%s", newLine, message, newLine);
    }

    /**
     * Test method Collection ArrayList(Fixed size).
     */
    @Test
    public void whenUsedCollectionArrayListFixedSizeAddAndRemoveThenGetExpectedSize() {
        getMessage("Collection ArrayList(fixed size = 3 mln): ");
        ArrayList<String> lineArrayListFixedSize = new ArrayList<>(numberAdd);
        PerfomanceCollections pfArrayListFixed = new PerfomanceCollections();
        resultTimeAdd = pfArrayListFixed.add(lineArrayListFixedSize, templateLine, numberAdd);
        resultTimeRemove = pfArrayListFixed.delete(lineArrayListFixedSize, numberRemove);
        System.out.printf(" -%s: %s %s%s", "operration add 3 mln line", resultTimeAdd, "ms;", newLine);
        System.out.printf(" -%s: %s %s%s", "operation remove 2,5 mln line", resultTimeRemove, "ms;", newLine);
        assertThat(lineArrayListFixedSize.size(), is(numberAdd - numberRemove));
    }

    /**
     * Test method Collection ArrayList.
     */
    @Test
    public void whenUsedCollectionArrayListAddAndRemoveThenGetExpectedSize() {
        getMessage("Collection ArrayList: ");
        ArrayList<String> lineArrayList = new ArrayList<>();
        PerfomanceCollections pfArrayList = new PerfomanceCollections();
        resultTimeAdd = pfArrayList.add(lineArrayList, templateLine, numberAdd);
        resultTimeRemove = pfArrayList.delete(lineArrayList, numberRemove);
        System.out.printf(" -%s: %s %s%s", "operration add 3 mln line", resultTimeAdd, "ms;", newLine);
        System.out.printf(" -%s: %s %s%s", "operation remove 2,5 mln line", resultTimeRemove, "ms;", newLine);
        String getExpectedElement = String.format("%s - number %s", templateLine, numberRemove);
        assertThat(lineArrayList.get(0), is(getExpectedElement));
    }

    /**
     * Test method Collection LinkedList.
     */
    @Test
    public void whenUsedCollectionLinkedListAddAndRemoveThenGetExpectedElement() {
        getMessage("Collection LinkedList: ");
        LinkedList<String> lineLinkedList = new LinkedList<>();
        PerfomanceCollections pfLinkedList = new PerfomanceCollections();
        resultTimeAdd = pfLinkedList.add(lineLinkedList, templateLine, numberAdd);
        resultTimeRemove = pfLinkedList.delete(lineLinkedList, numberRemove);
        System.out.printf(" -%s: %s %s%s", "operration add 3 mln line", resultTimeAdd, "ms;", newLine);
        System.out.printf(" -%s: %s %s%s", "operation remove 2,5 mln line", resultTimeRemove, "ms;", newLine);
        String getExpectedElement = String.format("%s - number %s", templateLine, numberRemove);
        assertThat(lineLinkedList.get(0), is(getExpectedElement));
    }

    /**
     * Test method Collection HashSet.
     */
    @Test
    public void whenUsedCollectionHashSetAddAndRemoveThenGetExpectedSize() {
        getMessage("Collection HashSet: ");
        HashSet<String> lineHashSet = new HashSet<>();
        PerfomanceCollections pfHashSet = new PerfomanceCollections();
        resultTimeAdd = pfHashSet.add(lineHashSet, templateLine, numberAdd);
        resultTimeRemove = pfHashSet.delete(lineHashSet, numberRemove);
        System.out.printf(" -%s: %s %s%s", "operration add 3 mln line", resultTimeAdd, "ms;", newLine);
        System.out.printf(" -%s: %s %s%s", "operation remove 2,5 mln line", resultTimeRemove, "ms;", newLine);
        assertThat(lineHashSet.size(), is(numberAdd - numberRemove));
    }

    /**
     * Test method Collection TreeSet.
     */
    @Test
    public void whenUsedCollectionTreeSetAddAndRemoveThenGetExpectedSize() {
        getMessage("Collection TreeSet: ");
        TreeSet<String> lineTreeSet = new TreeSet<>();
        PerfomanceCollections pfTreeSet = new PerfomanceCollections();
        resultTimeAdd = pfTreeSet.add(lineTreeSet, templateLine, numberAdd);
        resultTimeRemove = pfTreeSet.delete(lineTreeSet, numberRemove);
        System.out.printf(" -%s: %s %s%s", "operration add 3 mln line", resultTimeAdd, "ms;", newLine);
        System.out.printf(" -%s: %s %s%s", "operation remove 2,5 mln line", resultTimeRemove, "ms;", newLine);
        assertThat(lineTreeSet.size(), is(numberAdd - numberRemove));
    }
}