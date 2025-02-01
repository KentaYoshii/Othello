package Application.GameObject;

import Application.Constants;
import Application.Constants.PIECE_COLOR;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.BiConsumer;

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

    public GameBoardCell(JPanel panel, int rowIdx, int colIdx,
                         BiConsumer<Integer, Integer> onClickConsumer) {
        this.panel = panel;
        this.piece = new OthelloPiece();
        this.panel.add(piece);
        this.rowLoc = rowIdx;
        this.colLoc = colIdx;
        this.setClickListener(onClickConsumer, this.rowLoc, this.colLoc);
    }

    private void setClickListener(BiConsumer<Integer, Integer> onClickConsumer, Integer row, Integer col) {
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isPiecePlaced) {
                    return;
                }
                onClickConsumer.accept(row, col);
            }
        });
    }

    public void placePiece(PIECE_COLOR color) {
        this.isPiecePlaced = true;
        piece.setColor(color);
        piece.setVisible(true);
        piece.repaint();
    }

    public void flipPiece() {
        piece.flip();
    }

    public void highlightCell(boolean on) {
        panel.setBackground(on ? Constants.BOARD_CELL_HIGHLIGHT_BG : Constants.BOARD_CELL_BG);
        this.panel.repaint();
    }
}
