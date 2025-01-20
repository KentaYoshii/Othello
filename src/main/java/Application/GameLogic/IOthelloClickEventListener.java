package Application.GameLogic;

public interface IOthelloClickEventListener {

    /**
     * This method will get invoked whenever a player places a piece on the board.
     * Register the instance using {@link Application.GameObject.GameBoard} class's registerPostClickEventListener()
     * method
     * */
    void onPiecePlaced();
}
