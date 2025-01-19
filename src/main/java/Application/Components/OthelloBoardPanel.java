package Application.Components;

import Application.Constants;
import Application.GameObject.GameBoard;
import Application.GameObject.GameBoardCell;

import javax.swing.*;
import java.awt.*;

/**
 * OthelloBoardPanel class contains the board - a 8x8 grid of equal-sized JPanel objects
 * */
public class OthelloBoardPanel extends JPanel {

    private static final Dimension defaultCellDimension =
            new Dimension(Constants.BOARD_CELL_SIZE_X, Constants.BOARD_CELL_SIZE_Y);

    public OthelloBoardPanel(GameBoard gameBoard) {
        this.setBackground(Constants.BOARD_PANEL_BG);
        this.setLayout(new GridLayout(Constants.NUM_ROW, Constants.NUM_COL,
                Constants.BOARD_GRID_GAP, Constants.BOARD_GRID_GAP));
        this.setBorder(BorderFactory.createEmptyBorder(Constants.BOARD_GRID_GAP, Constants.BOARD_GRID_GAP,
                Constants.BOARD_GRID_GAP, Constants.BOARD_GRID_GAP));
        createBoard(this, gameBoard);
    }

    /**
     * Creates a 8x8 grid of Othello board inside "board"
     * @param boardPanel JPanel that will contain the board
     * @param gameBoard GameBoard we are populating
     * */
    private static void createBoard(JPanel boardPanel, GameBoard gameBoard) {
        GameBoardCell[][] board = gameBoard.getGameBoard();
        for (int row = 0; row < Constants.NUM_ROW; row++) {
            for (int col = 0; col < Constants.NUM_COL; col++) {
                // Set up a panel to represent a single cell in board
                JPanel panel = new JPanel();
                panel.setPreferredSize(defaultCellDimension);
                panel.setBackground(Constants.BOARD_CELL_BG);
                boardPanel.add(panel, -1);
                // Create a GameBoardCell object
                GameBoardCell cell = new GameBoardCell(panel, row, col);
                board[row][col] = cell;
            }
        }
    }
}
