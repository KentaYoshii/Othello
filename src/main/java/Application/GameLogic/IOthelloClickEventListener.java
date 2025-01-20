package Application.GameLogic;

import Application.Constants;

public interface IOthelloClickEventListener {

    /**
     * This method will get invoked whenever a player places a piece on the board.
     * Register the instance using {@link Application.GameObject.GameBoard} class's registerPostClickEventListener()
     * method
     * @param rowLoc Row location
     * @param colLoc Column location
     * @param color PIECE_COLOR of the piece
     * */
    void onPiecePlaced(int rowLoc, int colLoc, Constants.PIECE_COLOR color);
}
