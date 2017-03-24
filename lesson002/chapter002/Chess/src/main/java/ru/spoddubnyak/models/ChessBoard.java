package ru.spoddubnyak.models;

import ru.spoddubnyak.errors.ChessBoardException;
import ru.spoddubnyak.errors.FigureNotFoundException;
import ru.spoddubnyak.errors.ImpossibleMoveException;
import ru.spoddubnyak.errors.OccupiedWayException;

/**
 * Class ChessBoard implements the chess board object and possible actions with the object.
 *
 * @author @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 12.03.2016
 */
public class ChessBoard {
    /**
     * property - the number of the graphic used for the cell name.
     */
    private static final int NUMBERSYMBOL = 65;
    /**
     * property - chessboard size.
     */
    private static final int SIZE_BOARD = 8;

    /**
     * property - cell array realizing a chessboard.
     */
    private Cell[][] board = new Cell[SIZE_BOARD][SIZE_BOARD];


    /**
     * property - Created shapes.
     */
    private Figure[] figures = new Figure[2];

    /**
     * Getter property -  Created shapes.
     *
     * @return property -  Created shapes
     */
    public Figure[] getFigures() {
        Figure[] copyFigures = new Figure[this.figures.length];
        System.arraycopy(this.figures, 0, copyFigures, 0, this.figures.length);
        return copyFigures;
    }

    /**
     * Setter property -  array chess Figures.
     *
     * @param figures -  array chee Figures
     */
    public void setFigures(Figure[] figures) {
        Figure[] copyFigures = new Figure[figures.length];
        System.arraycopy(figures, 0, copyFigures, 0, figures.length);
        this.figures = copyFigures;
    }

    /**
     * Getter property -  chessboard.
     *
     * @return property -  chessboard
     */
    public Cell[][] getBoard() {
        Cell[][] cloneBoard = this.board.clone();
        return cloneBoard;
    }

    /**
     * Method initializes an array of cells - a chessboard.
     */
    public void initBoard() {
        for (int row = 0; row < this.board.length; row++) {
            for (int column = 0; column < this.board.length; column++) {
                this.board[row][column] = new Cell(String.format("%s%s", Character.toString((char) (NUMBERSYMBOL + column)), Integer.toString(this.board.length - row)));
            }
        }
    }

    /**
     * Method figuring out the pieces on the board.
     */
    public void createChesFigeres() {
        figures[0] = new Pawn(getCellOnBoardByNameCell("C3"));
        figures[1] = new Rook(getCellOnBoardByNameCell("A1"));
    }

    /**
     * Method figuring out the pieces on the board.
     *
     * @throws ChessBoardException if the cell is occupied by another figure.
     */
    public void fillFiguresOnBoard() throws ChessBoardException {
        for (int i = 0; i < figures.length; i++) {
            if (verificationEmploymentCell(figures[i].getPositionStartOnBoard())) {
                throw new ChessBoardException("It is impossible to install a figure, the cell is occupied by another figure.");
            }
            figures[i].getPositionStartOnBoard().setFigure(figures[i]);
        }
    }

    /**
     * Method get the address of the cell on the board by name.
     *
     * @param nameCell - name Cell
     * @return cell = adress Cell
     */
    public Cell getCellOnBoardByNameCell(String nameCell) {
        Cell cell = null;
        boolean resulSearch = false;
        for (int row = 0; row < board.length; row++) {
            if (resulSearch) {
                break;
            }
            for (int column = 0; column < board.length; column++) {
                if (this.board[row][column].getPosition().equals(nameCell)) {
                    cell = this.board[row][column];
                    resulSearch = true;
                    break;
                }
            }
        }
        return cell;
    }

    /**
     * Method Figure movement on the board.
     *
     * @param nameCellOnBoardStart  - name Cell of start start Figure.
     * @param nameCellOnBoardFinish - name Cell of finish start Figure.
     * @return result "true" if the start is possible and "false" if the start is impossible.
     * @throws ImpossibleMoveException if the start is impossible.
     * @throws FigureNotFoundException if the figure was not found at the start position.
     * @throws OccupiedWayException    - if Obstacle in the way.
     * @throws ImpossibleMoveException if the start is impossible.
     */
    public boolean move(String nameCellOnBoardStart, String nameCellOnBoardFinish) throws ImpossibleMoveException, FigureNotFoundException, OccupiedWayException {
        boolean globalValid = true;
        boolean findFigure = false;
        boolean impossibleValid = false;
        Cell startCell = getCellOnBoardByNameCell(nameCellOnBoardStart);
        Cell finishCell = getCellOnBoardByNameCell(nameCellOnBoardFinish);
        Cell[] pathCells;
        for (Figure figure : this.figures) {
            if (figure.getPositionStartOnBoard().equals(startCell)) {
                pathCells = figure.way(this.board, finishCell);
                findFigure = true;
                for (Cell cell : pathCells) {
                    if (cell.equals(finishCell)) {
                        impossibleValid = true;
                        break;
                    }
                }
                break;
            }
        }
        if (nameCellOnBoardFinish.equals(nameCellOnBoardStart)) {
            throw new ImpossibleMoveException("The start is impossible. The figure must change the cell.");
        }
        if (!findFigure) {
            throw new FigureNotFoundException("The figure at the initial position was not found.");
        }
        if (!impossibleValid) {
            throw new ImpossibleMoveException("The start is impossible.");
        }
        return globalValid;
    }

    /**
     * Method at the address of the cell, it checks if the cell is occupied by another figure.
     *
     * @param cell - address of the cell
     * @return result "fale" if the Cell obstacle or "true" the Cell clear .
     */
    public boolean verificationEmploymentCell(Cell cell) {
        boolean employment = false;
        if (cell.getFigure() != null) {
            employment = true;
        }
        return employment;
    }

    /**
     * Method cloning of the figure from the initial position to the final.
     *
     * @param nameCellOnBoardStart  - name Cell of start Figure
     * @param nameCellOnBoardFinish - name Cell of finish Figure
     * @throws ImpossibleMoveException if the start is impossible.
     * @throws FigureNotFoundException if the figure was not found at the start position.
     * @throws OccupiedWayException    - if Obstacle in the way.
     */
    public void cloneFgure(String nameCellOnBoardStart, String nameCellOnBoardFinish) throws OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        if (move(nameCellOnBoardStart, nameCellOnBoardFinish)) {
            Cell startCell = getCellOnBoardByNameCell(nameCellOnBoardStart);
            Cell finishCell = getCellOnBoardByNameCell(nameCellOnBoardFinish);
            Figure figure = startCell.getFigure();
            startCell.setFigure(null);
            figure.clone(finishCell);
        }
    }
}