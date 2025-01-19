package Application.Components;

import Application.Constants;
import Application.GameObject.GameBoard;

import javax.swing.*;
import java.awt.*;

/**
 * OthelloGamePanel contains the "content"
 * */
public class OthelloGamePanel extends JPanel {

    private static final Dimension defaultGamePanelDimension =
            new Dimension(Constants.GAME_PANEL_SIZE_X, Constants.GAME_PANEL_SIZE_Y);

    public OthelloGamePanel(GameBoard gameBoard) {
        this.setPreferredSize(defaultGamePanelDimension);
        this.setBackground(Constants.GAME_PANEL_BG);
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(
                Constants.GAME_BORDER_SIZE,
                Constants.GAME_BORDER_SIZE,
                Constants.GAME_BORDER_SIZE,
                Constants.GAME_BORDER_SIZE
        ));

        // Create Board Panel which will contain the Game Board
        OthelloBoardPanel boardPanel = new OthelloBoardPanel(gameBoard);
        this.add(boardPanel, BorderLayout.CENTER);
    }
}
