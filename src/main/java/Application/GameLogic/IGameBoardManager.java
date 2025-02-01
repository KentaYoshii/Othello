package Application.GameLogic;

import Application.Constants.GAME_MODE;

/**
 * Interface for GameBoardManager.
 * */
public interface IGameBoardManager {
    /**
     * Create a new game. All initialization happens here
     */
    void createNewGame(GAME_MODE mode);

    /**
     * Resets the game.
     * */
    void resetGame(GAME_MODE mode);
}
