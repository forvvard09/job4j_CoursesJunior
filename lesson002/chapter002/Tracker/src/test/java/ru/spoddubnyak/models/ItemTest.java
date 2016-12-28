package ru.spoddubnyak.models;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 26.12.2016
 */
public class ItemTest {
    /**  test - test.  */
    private Item item = new Item("", "", 00L);
    /**
    * Tests.
    */
    @Test
    public void whenSetIdCompliesThenGetId() {
        final int id = 99;
        item.setId(id);
        System.out.println(item.getId());
        assertThat(id, is(item.getId()));

    }
    /**
     * Tests.
     */
    @Test
    public void whenSetNameCompliesThenGetName() {
        String name = "testName";
        item.setName(name);
        assertThat(name, is(item.getName()));
    }
    /**
     * Tests.
     */
    @Test
    public void whenSetDescriptionCompliesThenGetDescription() {
        String description = "testDescription";
        item.setDescription(description);
        assertThat(description, is(item.getDescription()));
    }
    /**
     * Tests.
     */
    @Test
    public void whenSetCreateCompliesThenGetCreate() {
        final Long create = 111L;
        item.setCreate(create);
        assertThat(create, is(item.getCreate()));
    }
}


