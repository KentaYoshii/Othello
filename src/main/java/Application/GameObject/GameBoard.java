package Application.GameObject;

import Application.Components.OthelloGamePanel;
import Application.Constants;
import Application.GameLogic.IOthelloClickEventListener;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public final class GameBoard {

    // 8x8 Othello board
    @Getter
    private final GameBoardCell[][] gameBoard =
            new GameBoardCell[Constants.NUM_ROW][Constants.NUM_COL];

    private static final Dimension defaultCellDimension =
            new Dimension(Constants.BOARD_CELL_SIZE_X, Constants.BOARD_CELL_SIZE_Y);

    // JPanel containing the Othello Board
    private final JPanel boardPanel;

    // Supplier for the current turn
    Supplier<Constants.PIECE_COLOR> turnSupplier;

    // Listeners to notify after a cell is placed
    List<IOthelloClickEventListener> notifyees;

    public GameBoard(OthelloGamePanel gamePanel, Supplier<Constants.PIECE_COLOR> turnSupplier) {
        this.boardPanel = gamePanel.getBoardPanel();
        this.turnSupplier = turnSupplier;
        notifyees = new ArrayList<>();
        createBoard();
    }

    /**
     * Register and add a listener to list of notifyees
     * @param listener An instance of a IOthelloClickEventListener
     * */
    public void registerPostClickEventListener(IOthelloClickEventListener listener) {
        notifyees.add(listener);
    }

    /**
     * Initialize the board with 4 pieces set
     * - 2 white pieces at (3,3) (4,4)
     * - 2 black pieces at (3,4) (4,3)
     * */
    public void initializeBoard() {
        // Place the white pieces
        gameBoard[3][3].placePiece(Constants.PIECE_COLOR.WHITE);
        gameBoard[4][4].placePiece(Constants.PIECE_COLOR.WHITE);
        // Place the black pieces
        gameBoard[3][4].placePiece(Constants.PIECE_COLOR.BLACK);
        gameBoard[4][3].placePiece(Constants.PIECE_COLOR.BLACK);
    }

    /**
     * Returns true if a piece can be placed at (row, col). Note that no
     * input validations are performed since this is called only in the event listener cb
     * @param row Row location
     * @param col Column location
     * @return true if a piece can be placed
     * */
    public boolean canPlacePieceAt(int row, int col) {
        return !gameBoard[row][col].isPiecePlaced;
    }

    /**
     * Place a piece at (row, col) using "color".
     * @param row Row location
     * @param col Column location
     * @param color Color of the piece to be placed
     * */
    public void placePieceAt(int row, int col, Constants.PIECE_COLOR color) {
        this.gameBoard[row][col].placePiece(color);
    }

    /**
     * Flip a piece at (row, col) using "color".
     * @param row Row location
     * @param col Column location
     * */
    public void flipPieceAt(int row, int col) {
        this.gameBoard[row][col].flipPiece();
    }

    /**
     * Get the piece color at (row, col).
     * @param row Row location
     * @param col Column location
     * @return color of the piece at (row, col)
     * */
    public Constants.PIECE_COLOR getPieceColorAt(int row, int col) {
        return this.gameBoard[row][col].piece.color;
    }

    /**
     * Creates a 8x8 grid of Othello board inside "board". For each "cell", create a JPanel and a containing
     * GameBoardCell object.
     * */
    private void createBoard() {
        for (int row = 0; row < Constants.NUM_ROW; row++) {
            for (int col = 0; col < Constants.NUM_COL; col++) {
                // Set up a panel to represent a single cell in board
                JPanel panel = new JPanel();
                panel.setPreferredSize(defaultCellDimension);
                panel.setBackground(Constants.BOARD_CELL_BG);
                boardPanel.add(panel, -1);
                // Create a GameBoardCell object
                GameBoardCell cell = new GameBoardCell(panel, row, col, this::handleBoardCellClickEvent);
                gameBoard[row][col] = cell;
            }
        }
    }

    /**
     * MouseClick event listener callback. Notify the interested party of the coordinates and the color of the piece
     * placed.
     * @param row row location of the piece placed
     * @param col column location of the piece placed
     * */
    private void handleBoardCellClickEvent(int row, int col) {
        Constants.PIECE_COLOR currentTurn = turnSupplier.get();
        // Notify the interested parties
        for (IOthelloClickEventListener listener: notifyees) {
            listener.onPiecePlaced(row, col, currentTurn);
        }
    }
}
