package Application.GameObject;

import Application.Constants;
import Application.GameLogic.GameBoardManager;

public class HumanPlayer extends AbstractPlayer {

    GameBoardManager manager;

    public HumanPlayer(Constants.PIECE_COLOR color, GameBoardManager manager) {
        super(color);
        this.manager = manager;
    }

    @Override
    public void playTurn() {
        if (showHint) {
            // For Human Player, if we are in "showHint" mode, high light the cells
            manager.displayHints(color);
        }
    }

}
