package Application.Components;

import Application.Constants;

import javax.swing.*;
import java.awt.*;

/**
 * OthelloFrame is a custom JFrame class
 * */
public class OthelloFrame extends JFrame {

    public OthelloFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Othello Game");
        this.setVisible(true);
        this.setResizable(false);
        this.setLayout(new BorderLayout(Constants.FRAME_BORDER_WIDTH, 0));
        this.setMinimumSize(new Dimension(Constants.TOTAL_PANEL_SIZE_X, Constants.TOTAL_PANEL_SIZE_Y));
    }
}
