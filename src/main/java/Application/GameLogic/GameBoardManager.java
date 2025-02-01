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

import java.util.List;

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

    @Override
    public void createNewGame(GAME_MODE mode) {
        // Create the board
        gameBoard = new GameBoard(this.gamePanel, this.turnManager::getCurrentTurn);
        // Initialize the board
        gameBoard.initializeBoard();
        gameBoard.registerPostClickEventListener(this);
        currentTurn = Constants.INITIAL_TURN;
        // Initialize the players
        IPlayer player1 = new HumanPlayer(PIECE_COLOR.BLACK, this);
        IPlayer player2 = mode == GAME_MODE.HUMAN ?
                new HumanPlayer(PIECE_COLOR.WHITE, this) : new AI(PIECE_COLOR.WHITE);
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
    public void onPiecePlaced(int rowLoc, int colLoc, PIECE_COLOR color) {
        // 0. Validate
        boolean validMove = this.validateMove(rowLoc, colLoc, color);
        if (!validMove) {
            return;
        }
        // 0. Turn off highlights
        this.resetHighlights();
        // 1. Flip any pieces
        this.flipPieces(rowLoc, colLoc, color);
        // 2. Placed the piece
        this.gameBoard.placePieceAt(rowLoc, colLoc, color);
        // 2. Perform any updates
        // 3. Start a new turn
        this.turnManager.startNextTurn();
    }

    public void displayHints(PIECE_COLOR color) {
        List<List<Integer>> allPlaceableLocs = OthelloLogic.getPlaceableLocations(this.gameBoard, color);
        for (List<Integer> coords: allPlaceableLocs) {
            this.gameBoard.highlightCellAt(coords.get(0), coords.get(1), true);
        }
    }

    private void resetHighlights() {
        for (int row = 0; row < Constants.NUM_ROW; row++) {
            for (int col = 0; col < Constants.NUM_COL; col++) {
                this.gameBoard.highlightCellAt(row, col, false);
            }
        }
    }

    private void flipPieces(int row, int col, PIECE_COLOR color) {
        List<List<Integer>> results = OthelloLogic.evaluateBoard(this.gameBoard, color, row, col);
        for (List<Integer> res: results) {
            this.gameBoard.flipPieceAt(res.get(0), res.get(1));
        }
    }

    private boolean validateMove(int row, int col, PIECE_COLOR color) {
        // Piece is already placed
        if (!this.gameBoard.canPlacePieceAt(row, col)) {
            return false;
        }
        // Ensure the correct player is playing the turn
        return color == this.turnManager.getCurrentTurn();
    }
}
