package Application.GameLogic;

import Application.Constants.GAME_MODE;

/**
 * Interface for GameBoardManager.
 * */
public interface IGameBoardManager {
    /**
     * Evaluate the current state of the GameBoard.
     * @return true if the game is considered complete
     * */
    boolean evaluateBoard();

    /**
     * Perform any required actions after a player's turn end
     * */
    void onTurnEnd();

    /**
     * Create a new game. All initialization happens here
     */
    void createNewGame(GAME_MODE mode);

    /**
     * Resets the game.
     * */
    void resetGame(GAME_MODE mode);
}
