package Application.GameObject;

import Application.Constants.PIECE_COLOR;
import lombok.Getter;

public class AbstractPlayer implements IPlayer {

    @Getter
    PIECE_COLOR color;

    protected boolean showHint = true;

    public AbstractPlayer(PIECE_COLOR color) {
        this.color = color;
    }

    @Override
    public void playTurn() {}

    @Override
    public void skipTurn() {}
}
