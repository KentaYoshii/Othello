package Application.GameLogic;

import Application.Constants;
import Application.GameObject.IPlayer;

public interface ITurnManager {
    /**
     * Register the two IPlayers.
     * */
    void registerPlayers(IPlayer player1, IPlayer player2);

    /**
     * Unregister the two IPlayers.
     * */
    void unregisterPlayers();

    /**
     * Starts the game.
     * */
    void startGame();

    /**
     * Get current turn
     * */
    Constants.PIECE_COLOR getCurrentTurn();

    /**
     * Starts a new turn
     * */
    void startNextTurn();
}
