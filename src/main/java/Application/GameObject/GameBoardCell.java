package Application.GameObject;

import javax.swing.*;

public class GameBoardCell {
    // Panel representing the board cell
    JPanel panel;
    // 0-based cell location
    int rowLoc;
    int colLoc;
    // flag
    boolean isPiecePlaced;

    public GameBoardCell(JPanel panel, int rowIdx, int colIdx) {
        this.panel = panel;
        this.rowLoc = rowIdx;
        this.colLoc = colIdx;
    }
}
