package ru.spoddubnyak;

import org.junit.Test;
import ru.spoddubnyak.binareeTree.BinaryTree;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Tests class BinaryTree.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 20.09.2017
 */
public class BinaryTreeTest {
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
     * Test testing methods add(), getSize(), empty() and contains().
     */
    @Test
    public void whenAddOneElementThenExpectedResult() {
        BinaryTree<String> binaryTree = new BinaryTree<>();
        assertThat(binaryTree.empty(), is(true));
        binaryTree.add(testRootOne);
        assertThat(binaryTree.empty(), is(false));
        final int expectedSize = 1;
        assertThat(binaryTree.getSize(), is(expectedSize));
        assertThat(binaryTree.contains(testRootOne), is(true));
    }

    /**
     * Test testing constructor with new element, getSize(), empty() and contains().
     */
    @Test
    public void whenConstructorCreateNewElementThenExpectedResult() {
        BinaryTree<String> binaryTree = new BinaryTree<>(testRootOne);
        assertThat(binaryTree.empty(), is(false));
        final int expectedSize = 1;
        assertThat(binaryTree.getSize(), is(expectedSize));
        assertThat(binaryTree.contains(testRootOne), is(true));
    }

    /**
     * Test add dublicates.
     */
    @Test
    public void whenAddDublicatesThenExpectedResult() {
        BinaryTree<String> binaryTree = new BinaryTree<>(testRootOne);
        final int expectedSize = 1;
        assertThat(binaryTree.add(testRootOne), is(false));
        assertThat(binaryTree.getSize(), is(expectedSize));
        assertThat(binaryTree.contains(testRootOne), is(true));
    }

    /**
     * Test add elements.
     */
    @Test
    public void whenAddElementsThenExpectedResult() {
        BinaryTree<String> binaryTree = new BinaryTree<>(testRootOne);
        final int expectedSize = 5;
        assertThat(binaryTree.add(testChild), is(true));
        assertThat(binaryTree.add(testChildOne), is(true));
        assertThat(binaryTree.add(testChildSecond), is(true));
        assertThat(binaryTree.add(testChildThirds), is(true));
        assertThat(binaryTree.add(testChild), is(false));
        assertThat(binaryTree.add(testChildOne), is(false));
        assertThat(binaryTree.add(testChildSecond), is(false));
        assertThat(binaryTree.add(testChildThirds), is(false));
        assertThat(binaryTree.contains(testRootOne), is(true));
        assertThat(binaryTree.contains(testChild), is(true));
        assertThat(binaryTree.contains(testChildOne), is(true));
        assertThat(binaryTree.contains(testChildSecond), is(true));
        assertThat(binaryTree.contains(testChildThirds), is(true));
        assertThat(binaryTree.getSize(), is(expectedSize));
    }

    /**
     * Test class iterator in collections BinaryTree.
     */
    @Test
    public void whenIteratorReturnThenExpectedResult() {
        BinaryTree<String> binaryTree = new BinaryTree<>(testRootOne);
        final int expectedSize = 5;
        assertThat(binaryTree.add(testChild), is(true));
        assertThat(binaryTree.add(testChildOne), is(true));
        assertThat(binaryTree.add(testChildSecond), is(true));
        assertThat(binaryTree.add(testChildThirds), is(true));
        assertThat(binaryTree.getSize(), is(expectedSize));
        String[] expectedElements = new String[expectedSize];
        Iterator<String> myIterator = binaryTree.iterator();
        int i = 0;
        while (myIterator.hasNext()) {
            expectedElements[i++] = myIterator.next();
        }
        i = 0;
        assertThat(expectedElements[i++], is(testRootOne));
        assertThat(expectedElements[i++], is(testChild));
        assertThat(expectedElements[i++], is(testChildOne));
        assertThat(expectedElements[i++], is(testChildSecond));
        assertThat(expectedElements[i], is(testChildThirds));
        assertThat(expectedElements.length, is(binaryTree.getSize()));
    }
}
