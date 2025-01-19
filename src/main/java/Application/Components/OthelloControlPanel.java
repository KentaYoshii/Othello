package Application.Components;

import Application.Constants;

import javax.swing.*;
import java.awt.*;

public class OthelloControlPanel extends JPanel {

    private static final Dimension defaultControlPaneDimension =
            new Dimension(Constants.CONTROL_PANEL_SIZE_X, Constants.CONTROL_PANEL_SIZE_Y);


    public OthelloControlPanel() {
        this.setPreferredSize(defaultControlPaneDimension);
        this.setBackground(Constants.CONTROL_PANEL_BG);
    }
}
