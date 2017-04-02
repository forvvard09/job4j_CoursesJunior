package ru.spoddubnyak;


import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * Test class adding and removing entries from collections is required for further performance testing Collections: LinkedList, ArrayList, TreeSet.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 02.04.2017
 */
public class PerfomanceCollectionsTest {

    final static int numberAdd = 3000000;
    final static int numberRemove = 700000;
    final static String TEMPLATE_LINE = "Testing string for perfomance test";
    final static String NEW_LINE = System.getProperty("line.separator");

    private static void getMessage(String message) {
        System.out.printf("%s%s%s", NEW_LINE, message, NEW_LINE);
    }


    @Test
    public void whenUsedCollectionTreeSetAdd() {
        getMessage("Perfomating testing: add record in Collection:");
        TreeSet<String> lineTreeSet = new TreeSet<>();
        PerfomanceCollections pfTreeSet = new PerfomanceCollections();
        long resultTime = pfTreeSet.add(lineTreeSet, TEMPLATE_LINE, numberAdd);
        System.out.printf(" -%s: %s %s%s", "TreeSet         Collections add 3 mln line", resultTime, "ms;", NEW_LINE);
    }

    @Test
    public void whenUsedCollectionHashSetAdd() {
        HashSet<String> lineHashSet = new HashSet<>();
        PerfomanceCollections pfHashSet = new PerfomanceCollections();
        long resultTime = pfHashSet.add(lineHashSet, TEMPLATE_LINE, numberAdd);
        System.out.printf(" -%s: %s %s%s", "HashSet         Collections add 3 mln line", resultTime, "ms;", NEW_LINE);
    }

    @Test
    public void whenUsedCollectionArrayListAdd() {
        ArrayList<String> lineArrayList = new ArrayList<>(numberAdd);
        PerfomanceCollections pfArrayList = new PerfomanceCollections();
        long resultTime = pfArrayList.add(lineArrayList, TEMPLATE_LINE, numberAdd);
        System.out.printf(" -%s: %s %s%s", "ArrayList(3mln) Collections add 3 mln line", resultTime, "ms;", NEW_LINE);
    }

    @Test
    public void whenUsedCollectionArrayListFixedSizeAdd() {
        ArrayList<String> lineArrayList = new ArrayList<>(numberAdd);
        PerfomanceCollections pfArrayList = new PerfomanceCollections();
        long resultTime = pfArrayList.add(lineArrayList, TEMPLATE_LINE, numberAdd);
        System.out.printf(" -%s: %s %s%s", "ArrayList       Collections add 3 mln line", resultTime, "ms;", NEW_LINE);
    }

    @Test
    public void whenUsedCollectionLinkedListAdd() {
        LinkedList<String> lineLinkedList = new LinkedList<>();
        PerfomanceCollections pfLinkedList = new PerfomanceCollections();
        long resultTime = pfLinkedList.add(lineLinkedList, TEMPLATE_LINE, numberAdd);
        System.out.printf(" -%s: %s %s%s", "LinkedList      Collections add 3 mln line", resultTime, "ms;", NEW_LINE);
    }
}