package ru.spoddubnyak.models;

import ru.spoddubnyak.errors.ImpossibleMoveException;
import ru.spoddubnyak.errors.OccupiedWayException;
import ru.spoddubnyak.start.Move;

/**
 * Class Figures which implements figures on the chessboard, internal classes.
 *
 * @author @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 14.03.2016
 */
public class Figures {

    /**
     * create new object - Move.
     */
    private Move moves = new Move();

    /**
     * Geter of Object reference Move.
     *
     * @return moves
     */
    public Move getMoves() {
        return moves;
    }

    /**
     * Method returns an array of 2 elements, the position of the figure on the chessboard.
     *
     * @param board    - chess board
     * @param position - adress Cell
     * @return int[] - position of the figure horizontally and vertically on the board
     */
    public int[] getPositionOnBoard(Cell[][] board, Cell position) {
        int[] positionOnBoard = new int[2];
        boolean resulSearch = false;
        for (int row = 0; row < board.length; row++) {
            if (resulSearch) {
                break;
            }
            for (int column = 0; column < board.length; column++) {
                if (board[row][column].equals(position)) {
                    positionOnBoard[0] = row;
                    positionOnBoard[1] = column;
                    resulSearch = true;
                    break;
                }
            }
        }
        return positionOnBoard;
    }
}

/**
 * Inner Class Pawn. The properties and methods of the pawn object.
 *
 * @author @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 15.03.2016
 */
class Pawn extends Figure {

    /**
     * property - START_POSITION to determine the maximum stroke.
     */
    private static final int START_POSITION = 6;

    /**
     * Constructor of Pawn.
     *
     * @param positionStart start position of chess board
     */
    Pawn(Cell positionStart) {
        super(positionStart);
    }

    @Override
    protected Cell[] way(Cell[][] board, Cell finishCell) throws ImpossibleMoveException {

        Figures figure = new Figures();
        int[] startPositionOnBoard = new Figures().getPositionOnBoard(board, getPositionStartOnBoard());
        int startPositionColumn = startPositionOnBoard[0];
        int startPositionRow = startPositionOnBoard[1];
        int maxStep = START_POSITION == startPositionColumn ? 2 : 1;
        if ((startPositionColumn - maxStep) < 0 || (startPositionColumn + maxStep) > board.length) {
            throw new ImpossibleMoveException("We can not go beyond the board.");
        }
        Cell[] cellsOut = new Cell[maxStep + 1];
        cellsOut[0] = board[startPositionColumn][startPositionRow];
        figure.getMoves().movePawn(board, startPositionColumn, startPositionRow, cellsOut, maxStep);
        return cellsOut;
    }
}

/**
 * Inner Class Rook. The properties and methods of the pawn object.
 *
 * @author @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 15.03.2016
 */
class Rook extends Figure {
    /**
     * property - shift Column.
     */
    private int shiftColumn;
    /**
     * property - shift Row.
     */
    private int shiftRow;


    /**
     * Constructor of Rook.
     *
     * @param positionStart start position of chess board
     */
    Rook(Cell positionStart) {
        super(positionStart);
    }

    @Override
    protected Cell[] way(Cell[][] board, Cell finishCell) throws ImpossibleMoveException, OccupiedWayException {
        Figures figure = new Figures();
        int[] startPositionOnBoard = figure.getPositionOnBoard(board, getPositionStartOnBoard());
        int startPositionColumn = startPositionOnBoard[0];
        int startPositionRow = startPositionOnBoard[1];
        int[] finishPositionOnBoard = new Figures().getPositionOnBoard(board, finishCell);
        int finishPositionColumn = finishPositionOnBoard[0];
        int finishPositionRow = finishPositionOnBoard[1];
        int numberCell;
        if (finishPositionColumn < startPositionColumn) {
            if (finishPositionRow < startPositionRow) {
                numberCell = 0;
                int stepColumn = startPositionColumn;
                int stepRow = startPositionRow;
                while (stepColumn >= finishPositionColumn && stepRow >= 0 && stepColumn >= 0) {
                    numberCell++;
                    stepRow -= 1;
                    stepColumn -= 1;
                }
                shiftColumn = -1;
                shiftRow = -1;
            }
            if (finishPositionRow > startPositionRow) {
                numberCell = 0;
                int stepColumn = startPositionColumn;
                int stepRow = startPositionRow;
                while (stepColumn >= finishPositionColumn && stepRow <= (board.length - 1) && stepColumn <= (board.length - 1)) {
                    numberCell++;
                    stepRow += 1;
                    stepColumn -= 1;
                }
                shiftColumn = -1;
                shiftRow = 1;
            } else {
                throw new ImpossibleMoveException("We can not go beyond the board.");
            }
        } else {
            if (finishPositionColumn > startPositionColumn) {
                if (finishPositionRow < startPositionRow) {
                    numberCell = 0;
                    int stepColumn = startPositionColumn;
                    int stepRow = startPositionRow;
                    while (stepColumn <= finishPositionColumn && stepRow >= 0) {
                        System.out.println(board[stepColumn][stepRow].getPosition());
                        numberCell++;
                        stepRow -= 1;
                        stepColumn += 1;
                    }
                    shiftColumn = 1;
                    shiftRow = -1;
                }
                if (finishPositionRow > startPositionRow) {
                    numberCell = 0;
                    int stepColumn = startPositionColumn;
                    int stepRow = startPositionRow;
                    while (stepColumn <= finishPositionColumn && stepColumn <= (board.length - 1) && stepRow <= (board.length - 1)) {
                        numberCell++;
                        stepRow += 1;
                        stepColumn += 1;
                    }
                    shiftRow = 1;
                    shiftColumn = 1;
                } else {
                    throw new ImpossibleMoveException("We can not go beyond the board.");
                }
            } else {
                throw new ImpossibleMoveException("We can not go beyond the board.");
            }
        }
        Cell[] pathCells = new Cell[numberCell];
        pathCells[0] = getPositionStartOnBoard();
        figure.getMoves().moveRook(board, startPositionColumn, startPositionRow, numberCell, pathCells, shiftColumn, shiftRow);
        return pathCells;
    }
}