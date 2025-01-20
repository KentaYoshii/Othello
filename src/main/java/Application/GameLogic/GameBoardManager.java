package Application.GameLogic;

import Application.Components.OthelloGamePanel;
import Application.Constants;
import Application.Constants.PIECE_COLOR;
import Application.Constants.GAME_MODE;
import Application.GameObject.AI;
import Application.GameObject.GameBoard;
import Application.GameObject.HumanPlayer;
import Application.GameObject.IPlayer;
import lombok.Getter;

public class GameBoardManager implements IGameBoardManager, IOthelloClickEventListener {

    // In Othello, Black starts first
    @Getter
    PIECE_COLOR currentTurn;

    // Ongoing GameBoard
    GameBoard gameBoard;

    // GamePanel
    OthelloGamePanel gamePanel;

    // TurnManager
    ITurnManager turnManager;

    public GameBoardManager(OthelloGamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.turnManager = GameTurnManager.getOrCreateTurnManager();
    }

    /**
     * Flip the turn.
     * */
    public void changeTurn() {
        this.currentTurn = (this.currentTurn == PIECE_COLOR.BLACK) ? PIECE_COLOR.WHITE : PIECE_COLOR.BLACK;
    }

    @Override
    public boolean evaluateBoard() {
        return false;
    }

    @Override
    public void onTurnEnd() {

    }

    @Override
    public void createNewGame(GAME_MODE mode) {
        // Create the board
        gameBoard = new GameBoard(this.gamePanel, this.turnManager::getCurrentTurn);
        // Initialize the board
        gameBoard.initializeBoard();
        gameBoard.registerPostClickEventListener(this);
        currentTurn = Constants.INITIAL_TURN;
        // Initialize the players
        IPlayer player1 = new HumanPlayer(PIECE_COLOR.BLACK);
        IPlayer player2 = mode == GAME_MODE.HUMAN ? new HumanPlayer(PIECE_COLOR.WHITE) : new AI(PIECE_COLOR.WHITE);
        // Register the players
        turnManager.registerPlayers(player1, player2);
        turnManager.startGame();
    }

    @Override
    public void resetGame(GAME_MODE mode) {
        turnManager.unregisterPlayers();
        this.createNewGame(mode);
    }

    @Override
    public void onPiecePlaced() {
        // 1. Flip any pieces
        // 2. Perform any updates
        // 3. Start a new turn
        this.turnManager.startNextTurn();
    }
}
