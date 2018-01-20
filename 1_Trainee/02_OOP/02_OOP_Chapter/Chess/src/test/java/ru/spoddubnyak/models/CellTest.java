package ru.spoddubnyak.models;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test class Cell.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 21.03.2017
 */
public class CellTest {

    /**
     * property -  name position Cell.
     */
    private final String position = "testPosition";
    /**
     * property -  fugure.
     */
    private Figure testFigure;

    /**
     * Test setter and getter figure in Cell.
     */
    @Test
    public void whenSetFigureInCellThenGetFigureInCell() {
        Cell cell = new Cell();
        cell.setFigure(testFigure);
        assertThat(cell.getFigure(), is(testFigure));
    }

    /**
     * Test constructor class Cell and getter position in Cell.
     */
    @Test
    public void whenCreateObjectCellThenGetPositionInCell() {
        Cell cell = new Cell(position);
        assertThat(cell.getPosition(), is(position));
    }
}