package Application.GameLogic;

import Application.Constants;
import Application.GameObject.AbstractPlayer;
import Application.GameObject.IPlayer;

public class GameTurnManager implements ITurnManager {

    private GameTurnManager() {}

    // singleton
    private static GameTurnManager SINGLETON;

    // the players
    IPlayer player1;
    IPlayer player2;

    // the current player
    IPlayer currentPlayer;

    /**
     * Return a new/singleton GameTurnManager instance
     * @return singleton GameTurnManger
     * */
    public static ITurnManager getOrCreateTurnManager() {
        if (SINGLETON == null) {
            SINGLETON = new GameTurnManager();
        }
        return SINGLETON;
    }

    @Override
    public void registerPlayers(IPlayer player1, IPlayer player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public void unregisterPlayers() {
        this.player1 = null;
        this.player2 = null;
        this.currentPlayer = null;
    }

    @Override
    public void startGame() {
        currentPlayer = player1;
        player1.playTurn();
    }

    @Override
    public Constants.PIECE_COLOR getCurrentTurn() {
        AbstractPlayer player = (AbstractPlayer) this.currentPlayer;
        return player.getColor();
    }

    @Override
    public void startNextTurn() {
        // Update current player
        if (this.currentPlayer == this.player1) {
            this.currentPlayer = this.player2;
        } else {
            this.currentPlayer = this.player1;
        }
        // Play turn
        this.currentPlayer.playTurn();
    }
}
