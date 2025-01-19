package Application.GameObject;

import Application.Constants;
import lombok.Getter;

public final class GameBoard {

    // 8x8 Othello board
    @Getter
    private final GameBoardCell[][] gameBoard =
            new GameBoardCell[Constants.NUM_ROW][Constants.NUM_COL];

    boolean temp;

    public GameBoard() {}

}
