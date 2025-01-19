package Application.GameObject;

import Application.Constants.PIECE_COLOR;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameBoardCell {
    // Panel representing the board cell
    JPanel panel;
    // Panel representing the Othello piece
    OthelloPiece piece;
    // 0-based cell location
    int rowLoc;
    int colLoc;
    // flag
    boolean isPiecePlaced;

    public GameBoardCell(JPanel panel, int rowIdx, int colIdx) {
        this.panel = panel;
        this.piece = new OthelloPiece();
        this.panel.add(piece);
        this.rowLoc = rowIdx;
        this.colLoc = colIdx;
        this.setClickListener();
    }

    private void setClickListener() {
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isPiecePlaced) {
                    return;
                }
                // placePiece();
            }
        });
    }

    public void placePiece(PIECE_COLOR color) {
        this.isPiecePlaced = true;
        piece.setColor(color);
        piece.setVisible(true);
        piece.repaint();
    }
}
