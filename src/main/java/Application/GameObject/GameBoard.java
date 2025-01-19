package Application.GameObject;

import Application.Constants;
import lombok.Getter;

public final class GameBoard {

    // 8x8 Othello board
    @Getter
    private final GameBoardCell[][] gameBoard =
            new GameBoardCell[Constants.NUM_ROW][Constants.NUM_COL];

    public GameBoard() {}

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
}
