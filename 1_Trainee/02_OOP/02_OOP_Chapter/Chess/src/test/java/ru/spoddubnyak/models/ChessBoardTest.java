package ru.spoddubnyak.models;

import org.junit.Test;
import ru.spoddubnyak.errors.ChessBoardException;
import ru.spoddubnyak.errors.FigureNotFoundException;
import ru.spoddubnyak.errors.ImpossibleMoveException;
import ru.spoddubnyak.errors.OccupiedWayException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test class ChessBoard.
 *
 * @author @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 12.03.2016
 */
public class ChessBoardTest {

    /**
     * Test create chess Board and init board by name.
     */
    @Test
    public void whenGetPositionThenFindOnBoard() {
        String nameCell = "A1";
        ChessBoard board = new ChessBoard();
        board.initBoard();
        Cell expectedCell = board.getCellOnBoardByNameCell(nameCell);
        Cell receivedCell = null;
        boolean resulSearch = false;
        for (int row = 0; row < board.getBoard().length; row++) {
            if (resulSearch) {
                break;
            }
            for (int column = 0; column < board.getBoard().length; column++) {
                if (board.getBoard()[row][column].getPosition().equals(nameCell)) {
                    receivedCell = board.getBoard()[row][column];
                    resulSearch = true;
                    break;
                }
            }
        }
        assertThat(expectedCell, is(receivedCell));
    }

    /**
     * Test create init board and create array Figure.
     */
    @Test
    public void whenCreateFiguresThenFigureGetPositionOnboard() {
        boolean valid = false;
        ChessBoard board = new ChessBoard();
        board.initBoard();
        board.createChesFigeres();
        Figure[] figures = board.getFigures();
        if (!figures[0].getPositionStartOnBoard().equals(null)) {
            valid = true;
        }
        assertThat(valid, is(true));
    }

    /**
     * Test fill board figurs. Method fillFiguresOnBoard.
     *
     * @throws ChessBoardException if the cell is occupied by another figure.
     */
    @Test
    public void whenCellFigureOnBoardThenGetCellFigure() throws ChessBoardException {
        String nameCell = "A1";
        ChessBoard board = new ChessBoard();
        board.initBoard();
        board.createChesFigeres();
        Figure[] figures = new Figure[1];
        figures[0] = new Pawn(board.getCellOnBoardByNameCell(nameCell));
        board.fillFiguresOnBoard();
        Cell positionfigure = figures[0].getPositionStartOnBoard();
        boolean resulSearch = false;
        Cell receivedCell = null;
        for (int row = 0; row < board.getBoard().length; row++) {
            if (resulSearch) {
                break;
            }
            for (int column = 0; column < board.getBoard().length; column++) {
                if (board.getBoard()[row][column].getPosition().equals(nameCell)) {
                    receivedCell = board.getBoard()[row][column];
                    resulSearch = true;
                    break;
                }
            }
        }
        assertThat(positionfigure, is(receivedCell));
    }

    /**
     * Test trying to set a figure on the busy cell Get exception ChessBoardException.
     *
     * @throws ChessBoardException if the cell is occupied by another figure.
     */
    @Test(expected = ChessBoardException.class)
    public void whenSetFigureBusyCellThenGetChessBoardException() throws ChessBoardException {
        String nameCell = "A1";
        ChessBoard board = new ChessBoard();
        board.initBoard();
        board.createChesFigeres();
        Figure[] figures = new Figure[2];
        figures[0] = new Pawn(board.getCellOnBoardByNameCell(nameCell));
        figures[1] = new Rook(board.getCellOnBoardByNameCell(nameCell));
        board.setFigures(figures);
        board.fillFiguresOnBoard();
    }


    /**
     * Test successful course Rook.
     *
     * @throws ChessBoardException     if the cell is occupied by another figure.
     * @throws ImpossibleMoveException if the start is impossible.
     * @throws FigureNotFoundException if the figure was not found at the start position.
     * @throws OccupiedWayException    - if Obstacle in the way.
     */
    @Test
    public void whenSuccessfulMoveRookThenSuccessfulOutcome() throws ChessBoardException, OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        boolean validMove;
        String nameCellStart = "A1";
        String nameCellFinish = "C3";
        ChessBoard board = new ChessBoard();
        board.initBoard();
        board.createChesFigeres();
        Figure[] figures = new Figure[1];
        figures[0] = new Rook(board.getCellOnBoardByNameCell(nameCellStart));
        board.setFigures(figures);
        board.fillFiguresOnBoard();
        validMove = board.move(nameCellStart, nameCellFinish);
        assertThat(validMove, is(true));
    }

    /**
     * Test successful course Pawn.
     *
     * @throws ChessBoardException     if the cell is occupied by another figure.
     * @throws ImpossibleMoveException if the start is impossible.
     * @throws FigureNotFoundException if the figure was not found at the start position.
     * @throws OccupiedWayException    - if Obstacle in the way.
     */
    @Test
    public void whenSuccessfulMovePawnkThenExceptionFigureNotFoundException() throws ChessBoardException, OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        boolean validMove;
        String nameCellStart = "A1";
        String nameCellFinish = "A2";
        ChessBoard board = new ChessBoard();
        board.initBoard();
        board.createChesFigeres();
        Figure[] figures = new Figure[1];
        figures[0] = new Pawn(board.getCellOnBoardByNameCell(nameCellStart));
        board.setFigures(figures);
        board.fillFiguresOnBoard();
        validMove = board.move(nameCellStart, nameCellFinish);
        assertThat(validMove, is(true));
    }

    /**
     * Test there is no figure on the stroke position of the stroke.
     *
     * @throws ChessBoardException     if the cell is occupied by another figure.
     * @throws ImpossibleMoveException if the start is impossible.
     * @throws FigureNotFoundException if the figure was not found at the start position.
     * @throws OccupiedWayException    - if Obstacle in the way.
     */
    @Test(expected = FigureNotFoundException.class)
    public void whenErroneousMoveThenExceptionFigureNotFoundException() throws ChessBoardException, OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        String nameCellStartFigure = "A1";
        String nameCellStartMove = "D4";
        String nameCellFinish = "C3";
        ChessBoard board = new ChessBoard();
        board.initBoard();
        board.createChesFigeres();
        Figure[] figures = new Figure[1];
        figures[0] = new Rook(board.getCellOnBoardByNameCell(nameCellStartFigure));
        board.setFigures(figures);
        board.fillFiguresOnBoard();
        board.move(nameCellStartMove, nameCellFinish);
    }

    /**
     * Test obstacle the way is the figure.
     *
     * @throws ChessBoardException     if the cell is occupied by another figure.
     * @throws ImpossibleMoveException if the start is impossible.
     * @throws FigureNotFoundException if the figure was not found at the start position.
     * @throws OccupiedWayException    - if Obstacle in the way.
     */
    @Test(expected = OccupiedWayException.class)
    public void whenOnWayObstacleThenGetExceptionOccupiedWayException() throws ChessBoardException, OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        String nameCellStartFigure = "A1";
        String nameCellStartMove = "A1";
        String nameCellFinish = "C3";
        ChessBoard board = new ChessBoard();
        board.initBoard();
        board.createChesFigeres();
        Figure[] figures = new Figure[2];
        figures[0] = new Rook(board.getCellOnBoardByNameCell(nameCellStartFigure));
        figures[1] = new Pawn(board.getCellOnBoardByNameCell(nameCellFinish));
        board.setFigures(figures);
        board.fillFiguresOnBoard();
        board.move(nameCellStartMove, nameCellFinish);
    }


    /**
     * Test ove the figure in the same cage in which it stands, get exception ImpossibleMoveException.
     *
     * @throws ChessBoardException     if the cell is occupied by another figure.
     * @throws ImpossibleMoveException if the start is impossible.
     * @throws FigureNotFoundException if the figure was not found at the start position.
     * @throws OccupiedWayException    - if Obstacle in the way.
     */
    @Test(expected = ImpossibleMoveException.class)
    public void whenMoveFigureSameInWhichStandsThenGetImpossibleMoveException() throws ChessBoardException, OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        String nameCell = "A1";
        ChessBoard board = new ChessBoard();
        board.initBoard();
        board.createChesFigeres();
        Figure[] figures = new Figure[1];
        figures[0] = new Rook(board.getCellOnBoardByNameCell(nameCell));
        board.setFigures(figures);
        board.fillFiguresOnBoard();
        board.move(nameCell, nameCell);
    }

    /**
     * Test start the figure in the same cage in which it stands, get exception ImpossibleMoveException.
     *
     * @throws ChessBoardException     if the cell is occupied by another figure.
     * @throws ImpossibleMoveException if the start is impossible.
     * @throws FigureNotFoundException if the figure was not found at the start position.
     * @throws OccupiedWayException    - if Obstacle in the way.
     */
    @Test(expected = ImpossibleMoveException.class)
    public void whenTryingMakeImpossibleMoveFigureThenGetImpossibleMoveException() throws ChessBoardException, OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        String nameCellStart = "A1";
        String nameCellFinish = "B4";
        ChessBoard board = new ChessBoard();
        board.initBoard();
        board.createChesFigeres();
        Figure[] figures = new Figure[1];
        figures[0] = new Rook(board.getCellOnBoardByNameCell(nameCellStart));
        board.setFigures(figures);
        board.fillFiguresOnBoard();
        board.move(nameCellStart, nameCellFinish);
    }

    /**
     * Test check that after cloning a new address was registered in the figure.
     *
     * @throws ChessBoardException     if the cell is occupied by another figure.
     * @throws ImpossibleMoveException if the start is impossible.
     * @throws FigureNotFoundException if the figure was not found at the start position.
     * @throws OccupiedWayException    - if Obstacle in the way.
     */
    @Test()
    public void whenFinishAdressCellThenGetAdressCloneFigure() throws ChessBoardException, OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        String nameCellStart = "A1";
        String nameCellFinish = "C3";
        ChessBoard board = new ChessBoard();
        board.initBoard();
        board.createChesFigeres();
        Figure[] figures = new Figure[1];
        figures[0] = new Rook(board.getCellOnBoardByNameCell(nameCellStart));
        board.setFigures(figures);
        board.fillFiguresOnBoard();
        board.cloneFgure(nameCellStart, nameCellFinish);
        Cell expectedResult = figures[0].getPositionStartOnBoard();
        board.getCellOnBoardByNameCell(nameCellFinish);
        assertThat(board.getCellOnBoardByNameCell(nameCellFinish), is(expectedResult));
    }

    /**
     * Test after the movement of the figure, the head cell is released.
     *
     * @throws ChessBoardException     if the cell is occupied by another figure.
     * @throws ImpossibleMoveException if the start is impossible.
     * @throws FigureNotFoundException if the figure was not found at the start position.
     * @throws OccupiedWayException    - if Obstacle in the way.
     */
    @Test()
    public void whenMovementFigureThenGetStratCellreleased() throws ChessBoardException, OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        String nameCellStart = "A1";
        String nameCellFinish = "C3";
        ChessBoard board = new ChessBoard();
        board.initBoard();
        board.createChesFigeres();
        Figure[] figures = new Figure[1];
        figures[0] = new Rook(board.getCellOnBoardByNameCell(nameCellStart));
        board.setFigures(figures);
        board.fillFiguresOnBoard();
        board.cloneFgure(nameCellStart, nameCellFinish);
        boolean valid = board.verificationEmploymentCell(board.getCellOnBoardByNameCell(nameCellStart));
        assertThat(valid, is(false));
    }

    /**
     * Test after the successful completion of the start, we assign the figure finish Cell.
     *
     * @throws ChessBoardException     if the cell is occupied by another figure.
     * @throws ImpossibleMoveException if the start is impossible.
     * @throws FigureNotFoundException if the figure was not found at the start position.
     * @throws OccupiedWayException    - if Obstacle in the way.
     */
    @Test()
    public void whenFinishCellThenGetFigurePosition() throws ChessBoardException, OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        String nameCellStart = "A1";
        String nameCellFinish = "C3";
        ChessBoard board = new ChessBoard();
        board.initBoard();
        board.createChesFigeres();
        Figure[] figures = new Figure[1];
        figures[0] = new Rook(board.getCellOnBoardByNameCell(nameCellStart));
        board.setFigures(figures);
        board.fillFiguresOnBoard();
        board.cloneFgure(nameCellStart, nameCellFinish);
        assertThat(figures[0].getPositionStartOnBoard(), is(board.getCellOnBoardByNameCell(nameCellFinish)));
    }

    /**
     * Test emulation of the pawn at the maximum step, if the initial figure is on the second cell.
     *
     * @throws ChessBoardException     if the cell is occupied by another figure.
     * @throws ImpossibleMoveException if the start is impossible.
     * @throws FigureNotFoundException if the figure was not found at the start position.
     * @throws OccupiedWayException    - if Obstacle in the way.
     */
    @Test()
    public void whenMovePawnThenGetFigurePositionFinishCell() throws ChessBoardException, OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        String nameCellStart = "C2";
        String nameCellFinish = "C4";
        ChessBoard board = new ChessBoard();
        board.initBoard();
        board.createChesFigeres();
        Figure[] figures = new Figure[1];
        figures[0] = new Pawn(board.getCellOnBoardByNameCell(nameCellStart));
        board.setFigures(figures);
        board.fillFiguresOnBoard();
        board.cloneFgure(nameCellStart, nameCellFinish);
        assertThat(figures[0].getPositionStartOnBoard(), is(board.getCellOnBoardByNameCell(nameCellFinish)));
    }

    /**
     * Test emulation of erroneous motion.
     *
     * @throws ChessBoardException     if the cell is occupied by another figure.
     * @throws ImpossibleMoveException if the start is impossible.
     * @throws FigureNotFoundException if the figure was not found at the start position.
     * @throws OccupiedWayException    - if Obstacle in the way.
     */
    @Test(expected = ImpossibleMoveException.class)
    public void whenErrorMoveOneThenGetImpossibleMoveException() throws ChessBoardException, OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        String nameCellStart = "A1";
        String nameCellFinish = "A8";
        ChessBoard board = new ChessBoard();
        board.initBoard();
        board.createChesFigeres();
        Figure[] figures = new Figure[1];
        figures[0] = new Rook(board.getCellOnBoardByNameCell(nameCellStart));
        board.setFigures(figures);
        board.fillFiguresOnBoard();
        board.move(nameCellStart, nameCellFinish);
    }

    /**
     * Test emulation of erroneous motion.
     *
     * @throws ChessBoardException     if the cell is occupied by another figure.
     * @throws ImpossibleMoveException if the start is impossible.
     * @throws FigureNotFoundException if the figure was not found at the start position.
     * @throws OccupiedWayException    - if Obstacle in the way.
     */
    @Test(expected = ImpossibleMoveException.class)
    public void whenErrorMoveTwoThenGetImpossibleMoveException() throws ChessBoardException, OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        String nameCellStart = "E4";
        String nameCellFinish = "B8";
        ChessBoard board = new ChessBoard();
        board.initBoard();
        board.createChesFigeres();
        Figure[] figures = new Figure[1];
        figures[0] = new Rook(board.getCellOnBoardByNameCell(nameCellStart));
        board.setFigures(figures);
        board.fillFiguresOnBoard();
        board.move(nameCellStart, nameCellFinish);
    }

    /**
     * Test emulation of erroneous motion.
     *
     * @throws ChessBoardException     if the cell is occupied by another figure.
     * @throws ImpossibleMoveException if the start is impossible.
     * @throws FigureNotFoundException if the figure was not found at the start position.
     * @throws OccupiedWayException    - if Obstacle in the way.
     */
    @Test(expected = ImpossibleMoveException.class)
    public void whenErrorMoveThreeThenGetImpossibleMoveException() throws ChessBoardException, OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        String nameCellStart = "B5";
        String nameCellFinish = "B1";
        ChessBoard board = new ChessBoard();
        board.initBoard();
        board.createChesFigeres();
        Figure[] figures = new Figure[1];
        figures[0] = new Rook(board.getCellOnBoardByNameCell(nameCellStart));
        board.setFigures(figures);
        board.fillFiguresOnBoard();
        board.move(nameCellStart, nameCellFinish);
    }

    /**
     * Test emulation of erroneous motion.
     *
     * @throws ChessBoardException     if the cell is occupied by another figure.
     * @throws ImpossibleMoveException if the start is impossible.
     * @throws FigureNotFoundException if the figure was not found at the start position.
     * @throws OccupiedWayException    - if Obstacle in the way.
     */
    @Test(expected = ImpossibleMoveException.class)
    public void whenErrorMoveFourThenGetImpossibleMoveException() throws ChessBoardException, OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        String nameCellStart = "B3";
        String nameCellFinish = "B7";
        ChessBoard board = new ChessBoard();
        board.initBoard();
        board.createChesFigeres();
        Figure[] figures = new Figure[1];
        figures[0] = new Rook(board.getCellOnBoardByNameCell(nameCellStart));
        board.setFigures(figures);
        board.fillFiguresOnBoard();
        board.move(nameCellStart, nameCellFinish);
    }

    /**
     * Test emulation of erroneous motion.
     *
     * @throws ChessBoardException     if the cell is occupied by another figure.
     * @throws ImpossibleMoveException if the start is impossible.
     * @throws FigureNotFoundException if the figure was not found at the start position.
     * @throws OccupiedWayException    - if Obstacle in the way.
     */
    @Test(expected = ImpossibleMoveException.class)
    public void whenErrorMovefiveThenGetImpossibleMoveException() throws ChessBoardException, OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        String nameCellStart = "D6";
        String nameCellFinish = "A2";
        ChessBoard board = new ChessBoard();
        board.initBoard();
        board.createChesFigeres();
        Figure[] figures = new Figure[1];
        figures[0] = new Rook(board.getCellOnBoardByNameCell(nameCellStart));
        board.setFigures(figures);
        board.fillFiguresOnBoard();
        board.move(nameCellStart, nameCellFinish);
    }

}