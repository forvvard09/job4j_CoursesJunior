package ru.spoddubnyak.models;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests class Items.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 2.0
 * @since 20.01.2017
 */
public class ItemTest {
    /**
     * test property item.
     */
    private Item item = new Item("", "", 00L);

    /**
     * Tests setter and getter property id.
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


