package Application.GameObject;

import Application.Constants.PIECE_COLOR;
import lombok.Getter;

public class AbstractPlayer implements IPlayer {

    PIECE_COLOR color;

    public AbstractPlayer(PIECE_COLOR color) {
        this.color = color;
    }

    public PIECE_COLOR getColor() {
        return this.color;
    }

    @Override
    public void playTurn() {}

    @Override
    public void skipTurn() {}
}
