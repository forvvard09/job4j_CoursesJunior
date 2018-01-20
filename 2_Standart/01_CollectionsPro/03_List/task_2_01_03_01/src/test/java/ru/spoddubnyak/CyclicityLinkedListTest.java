package ru.spoddubnyak;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Tests class CyclicityLinkedList.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 20.07.2017
 */
public class CyclicityLinkedListTest {
    /**
     * Test create cyclic linked List.
     */
    @Test
    public void whenListCyclicThenGetExpectedResult() {
        CyclicityLinkedList node = new CyclicityLinkedList();
        final int exepcetedResult = 1;
        assertThat(node.hasCycle(node.createsNode(true)), is(true));
        int result = (int) node.getValue(node.getFirst());
        assertThat(result, is(exepcetedResult));
    }

    /**
     * Test create not cyclic linked List.
     */
    @Test
    public void whenListNotCyclicThenGetExpectedResult() {
        CyclicityLinkedList node = new CyclicityLinkedList();
        final int exepcetedResult = 1;
        assertThat(node.hasCycle(node.createsNode(false)), is(false));
        int result = (int) node.getValue(node.getFirst());
        assertThat(result, is(exepcetedResult));
    }
}