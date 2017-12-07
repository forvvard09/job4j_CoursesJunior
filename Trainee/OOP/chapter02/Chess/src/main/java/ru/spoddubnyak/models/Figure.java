package ru.spoddubnyak.models;

import ru.spoddubnyak.errors.ImpossibleMoveException;
import ru.spoddubnyak.errors.OccupiedWayException;

/**
 * Abstract class for Chess figure.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 14.03.2017
 */
public abstract class Figure {
    /**
     * property - position of the figure on the board.
     */
    private Cell positionStartOnBoard;

    /**
     * Constructor of Figure.
     *
     * @param positionStartOnBoard position of the figure on the board
     */
    Figure(Cell positionStartOnBoard) {
        this.positionStartOnBoard = positionStartOnBoard;
    }

    /**
     * Geter of position on board.
     *
     * @return positionStartOnBoard - Cell on board
     */
    public Cell getPositionStartOnBoard() {
        return this.positionStartOnBoard;
    }

    /**
     * Abstract method of realizes the movement of the figure in the bulletin board.
     *
     * @param board      chess board
     * @param finishCell Cell to go to finish
     * @return an array of cells that can be traversed by a figure
     * @throws OccupiedWayException    - if Obstacle in the way.
     * @throws ImpossibleMoveException if the start is impossible.
     */
    abstract Cell[] way(Cell[][] board, Cell finishCell) throws OccupiedWayException, ImpossibleMoveException;

    /**
     * Method assigns a new position to the initial position of the figure.
     *
     * @param finishCell chess board
     */
    public void clone(Cell finishCell) {
        this.positionStartOnBoard = finishCell;
    }
}