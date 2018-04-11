package ru.spoddubnyak;

import org.junit.Before;
import org.junit.Test;
import ru.spoddubnyak.Exceptions.OplimisticException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test class for  CacheNonBlocking.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 11.04.2018
 */
public class CacheNonBlockingTest {
    /**
     * property -  modelOne object class of Model for testing.
     */
    private Model modelOne;
    /**
     * property -  modelTwo object class of Model for testing.
     */
    private Model modelTwo;
    /**
     * property -  cache object class of CacheNonBlocking for testing.
     */
    private CacheNonBlocking<Integer> cache;
    /**
     * property -  keyFirst key for add to cache.
     */
    private final int keyFirst = 1;
    /**
     * property -  keySecond key for add to cache.
     */
    private final int keySecond = 2;

    /**
     * Method for init test data.
     */
    @Before
    public void generateTestData() {
        modelOne = new Model("one");
        modelTwo = new Model("two");
        cache = new CacheNonBlocking<>();
        cache.add(keyFirst, modelOne);
        cache.add(keySecond, modelTwo);
    }

    /**
     * Test add element to CacheNonBlocking.
     */
    @Test
    public void whenAddingThenExpectedSize() {
        final int expectedSize = 2;
        assertThat(this.cache.getSize(), is(expectedSize));
    }

    /**
     * Test delete element from CacheNonBlocking.
     */
    @Test
    public void whenRemovingThenExpectedSize() {
        final int expectedSize = 1;
        this.cache.delete(this.keyFirst);
        assertThat(this.cache.getSize(), is(expectedSize));
    }

    /**
     * Test update element in CacheNonBlocking by key.
     */
    @Test
    public void whenUpdatingThenExpectedResult() {
        final String testTest = "updateTest";
        assertThat(this.cache.update(this.keyFirst, testTest), is(true));
        assertThat(this.cache.getModel(this.keyFirst).getField(), is(testTest));
    }

    /**
     * Test update not such element in CacheNonBlocking by key.
     */
    @Test
    public void whenUpdatingNotSuchElementThenExpectedResult() {
        final String testTest = "updateTest";
        final int testIndex = 99;
        assertThat(this.cache.update(testIndex, testTest), is(false));
    }

    /**
     * Test update element in CacheNonBlocking by key, change version, get expected error.
     */
    @Test(expected = OplimisticException.class)
    public void whenUpdateElementWithChangeVersionThenExpectedError() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final int timeout = 3000;
                cache.testStateOnOff(true, timeout);
                for (;;) {
                    cache.getModel(1).incrementVersion();
                }
            }
        }).start();

        cache.update(1, "999");
    }
}
