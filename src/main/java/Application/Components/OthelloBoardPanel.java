package Application.Components;

import Application.Constants;

import javax.swing.*;
import java.awt.*;

/**
 * OthelloBoardPanel class contains the board - a 8x8 grid of equal-sized JPanel objects
 * */
public class OthelloBoardPanel extends JPanel {

    public OthelloBoardPanel() {
        this.setBackground(Constants.BOARD_PANEL_BG);
        this.setLayout(new GridLayout(Constants.NUM_ROW, Constants.NUM_COL,
                Constants.BOARD_GRID_GAP, Constants.BOARD_GRID_GAP));
        this.setBorder(BorderFactory.createEmptyBorder(Constants.BOARD_GRID_GAP, Constants.BOARD_GRID_GAP,
                Constants.BOARD_GRID_GAP, Constants.BOARD_GRID_GAP));
    }
}
