package ru.spoddubnyak.models;

/**
 * Class Cell describes the cell on the board.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 14.03.2017
 */
public class Cell {
    /**
     * property -  fugure.
     */
    private Figure figure;
    /**
     * property -  name position Cell.
     */
    private String position;

    /**
     * Constructor it creates a new object with the specified values.
     *
     * @param position - name position Cell
     */
    public Cell(String position) {
        this.position = position;
    }

    /**
     * Constructor it creates a new object with the specified values.
     */
    public Cell() {
    }

    /**
     * Geter get position.
     *
     * @return position
     */
    public String getPosition() {
        return this.position;
    }

    /**
     * Geter get figure.
     *
     * @return figure
     */
    public Figure getFigure() {
        return figure;
    }

    /**
     * Seter get figure.
     *
     * @param figure - figure
     */
    public void setFigure(Figure figure) {
        this.figure = figure;
    }
}