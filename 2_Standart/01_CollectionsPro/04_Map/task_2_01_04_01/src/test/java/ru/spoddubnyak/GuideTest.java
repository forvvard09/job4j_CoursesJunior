package ru.spoddubnyak;

import org.junit.Before;
import org.junit.Test;
import ru.spoddubnyak.guide.Guide;
import ru.spoddubnyak.guide.errors.NotFindsElementsInCollections;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Tests class Guide.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 30.08.2017
 */
public class GuideTest {
    /**
     * property for object Guide.
     */
    private Guide guide = new Guide();

    /**
     * property test array for key.
     */
    private String[] testKeyArr;
    /**
     * property test value.
     */
    private String testValueOne = "testValueOne";
    /**
     * property test value.
     */
    private String testValueTwo = "testValueTwo";
    /**
     * property test value.
     */
    private String testValueThree = "testValueThree";

    /**
     * Method for initialization test dates.
     */
    @Before
    public void initTestData() {
        String[] testKeyArr = {"1", "2", "3"};
        this.testKeyArr = testKeyArr;
    }

    /**
     * Test method bolean insert() and int getSize().
     */
    @Test
    public void whenInsertInGuideThenGetExpectedResult() {
        initTestData();
        int indexKey = 0;
        final int expectedSize = 2;
        assertThat(guide.insert(testKeyArr[indexKey], testValueOne), is(true));
        assertThat(guide.insert(testKeyArr[indexKey], testValueOne), is(false));
        assertThat(guide.insert(testKeyArr[indexKey++], testValueTwo), is(false));
        assertThat(guide.insert(testKeyArr[indexKey], testValueOne), is(true));
        assertThat(guide.getSize(), is(expectedSize));
    }

    /**
     * Test methods bolean insert() and V get(T key) .
     */
    @Test
    public void whenGetInsertGuideThenGetExpectedResult() {
        int indexKey = 0;
        assertThat(guide.insert(testKeyArr[indexKey++], testValueOne), is(true));
        assertThat(guide.insert(testKeyArr[indexKey], testValueTwo), is(true));
        assertThat(guide.get(testKeyArr[indexKey--]), is(testValueTwo));
        assertThat(guide.get(testKeyArr[indexKey]), is(testValueOne));
    }

    /**
     * Test methods boolean insert() and V get(T key) .
     */
    @Test
    public void whenGetInsertAndDeleteEntryGuideThenExpectedResult() {
        int indexKey = 0;
        guide.insert(testKeyArr[indexKey++], testValueOne);
        guide.insert(testKeyArr[indexKey++], testValueTwo);
        guide.insert(testKeyArr[indexKey], testValueThree);
        assertThat(guide.get(testKeyArr[indexKey--]), is(testValueThree));
        assertThat(guide.get(testKeyArr[indexKey++]), is(testValueTwo));
        assertThat(guide.delete(testKeyArr[indexKey--]), is(true));
        assertThat(guide.getSize(), is(2));
        assertThat(guide.delete(indexKey--), is(true));
        assertThat(guide.delete(indexKey), is(false));
        assertThat(guide.getSize(), is(1));
    }

    /**
     * Test methods get(key0 .
     */
    @Test(expected = NotFindsElementsInCollections.class)
    public void whenGetKeyElementNotFindInGuideThenGetError() {
        guide.get("3");
    }

    /**
     * Test increase size hash table.
     */
    @Test
    public void whenThen() {
        Guide guide = new Guide(2);
        final int lengthHashTable = 8;
        final int expectedNumberEntry = 5;
        for (int i = 1; i <= expectedNumberEntry; i++) {
            guide.insert(i, "testEntry");
        }
        assertThat(guide.getSizeHashTable(), is(lengthHashTable));
        assertThat(guide.getSize(), is(expectedNumberEntry));
    }


    /**
     * Test iterator and methods iterators in class Guide.
     */
    @Test
    public void whenIteratorObjectThenGetExpectedResult() {
        int indexKey = 0;
        guide.insert(testKeyArr[indexKey++], testValueOne);
        guide.insert(testKeyArr[indexKey++], testValueOne);
        guide.insert(testKeyArr[indexKey], testValueOne);
        String[] expectedResult = new String[guide.getSize()];
        Iterator myIterator = guide.iterator();
        int i = 0;
        indexKey = 0;
        while (myIterator.hasNext()) {
            expectedResult[i++] = myIterator.next().toString();
        }
        for (String str : expectedResult) {
            assertThat(String.format("%s:%s, %s:%s", "key", testKeyArr[indexKey++], "value", testValueOne), is(str));
        }
        assertThat(guide.getSize(), is(expectedResult.length));
    }
}
