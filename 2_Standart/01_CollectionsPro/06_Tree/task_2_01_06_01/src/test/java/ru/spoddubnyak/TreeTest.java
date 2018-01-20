package ru.spoddubnyak;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Tests class Tree.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 12.09.2017
 */
public class TreeTest {
    /**
     * property  test data.
     */
    private String testRootOne = "root";
    /**
     * property  test data.
     */
    private String testRootSecond = "root 1";
    /**
     * property  test data.
     */
    private String testChild = "child";
    /**
     * property  test data.
     */
    private String testChildOne = "child 1";
    /**
     * property  test data.
     */
    private String testChildSecond = "child 2";

    /**
     * property  test data.
     */
    private String testChildThirds = "child 3";

    /**
     * Test testing methods initialRoot, check add dublicates and verifies the existence of a parent.
     */
    @Test
    public void whenCreateTreeAndAddElementsThenExpectedNumberElements() {
        Tree<String> tree = new Tree<>();
        assertThat(tree.initialRoot(testRootOne), is(true));
        tree.initialRoot(testRootOne);
        assertThat(tree.add(testRootOne, testChild), is(true));
        assertThat(tree.add(testRootSecond, testChild), is(false));
        assertThat(tree.add(testRootOne, testChildOne), is(true));
        assertThat(tree.add(testRootOne, testChildOne), is(false));
        assertThat(tree.add(testRootOne, testRootOne), is(false));
        final int expectedSize = 3;
        assertThat(tree.getSize(), is(expectedSize));
    }

    /**
     * Test testing constructor Tree(E value), methods initialRoot, check add dublicates and verifies the existence of a parent.
     */
    @Test
    public void whenCreateConstructorTreeAndAddElementsThenExpectedNumberElements() {
        Tree<String> tree = new Tree<>(testRootOne);
        assertThat(tree.initialRoot(testRootSecond), is(false));
        assertThat(tree.add(testRootOne, testChild), is(true));
        assertThat(tree.add(testChild, testChildOne), is(true));
        assertThat(tree.add(testChildOne, testChildOne), is(false));
        assertThat(tree.add(testChildOne, testChildSecond), is(true));
        final int expectedSize = 4;
        assertThat(tree.getSize(), is(expectedSize));
    }

    /**
     * Test testing iterator for Tree.
     */
    @Test
    public void whenRunIteratorThenGetExpectedResult() {
        Tree<String> tree = new Tree<>(testRootOne);
        final int expectedSize = 4;
        String[] expectedElements = new String[expectedSize];
        assertThat(tree.add(testRootOne, testChild), is(true));
        assertThat(tree.add(testChild, testChildOne), is(true));
        assertThat(tree.add(testChildOne, testChildSecond), is(true));
        Iterator<String> myIterator = tree.iterator();
        int countIterator = 0;
        while (myIterator.hasNext()) {
            expectedElements[countIterator++] = myIterator.next();
        }
        countIterator = 0;
        assertThat(expectedElements[countIterator++], is(testRootOne));
        assertThat(expectedElements[countIterator++], is(testChild));
        assertThat(expectedElements[countIterator++], is(testChildOne));
        assertThat(expectedElements[countIterator], is(testChildSecond));
        assertThat(tree.getSize(), is(expectedSize));
    }

    /**
     * Test testing method isisBinary(), when Tree - binary.
     */
    @Test
    public void whenTreeBinaryThenGetExpectedResult() {
        Tree<String> tree = new Tree<>(testRootOne);
        tree.add(testRootOne, testChild);
        tree.add(testChild, testChildOne);
        tree.add(testChild, testChildSecond);
        tree.add(testRootOne, testRootSecond);
        assertThat(tree.isBinary(), is(true));
    }

    /**
     * Test testing method isisBinary(), when Tree - not binary.
     */
    @Test
    public void whenNotTreeBinaryThenGetExpectedResult() {
        Tree<String> tree = new Tree<>(testRootOne);
        tree.add(testRootOne, testChild);
        tree.add(testChild, testChildOne);
        tree.add(testChild, testChildSecond);
        tree.add(testChild, testChildThirds);
        assertThat(tree.isBinary(), is(false));
    }


}
