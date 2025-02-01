package Application.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

import Application.Constants;
import Application.Constants.PIECE_COLOR;
import lombok.Setter;

import static Application.Constants.PIECE_SIZE;

public class OthelloPiece extends JPanel {

    @Setter
    PIECE_COLOR color;

    public OthelloPiece() {
        setPreferredSize(new Dimension(PIECE_SIZE, PIECE_SIZE));
        setVisible(false);
        setBackground(Constants.BOARD_CELL_BG);
    }

    /**
     * Draws a circle. Credit to Claude.ai for generating this part of the code
     * */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Enable antialiasing for smoother circles
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Calculate the center point of the panel
        double centerX = getWidth() / 2.0;
        double centerY = getHeight() / 2.0;

        // Calculate piece diameter (slightly smaller than the cell)
        double diameter = Math.min(getWidth(), getHeight()) * 0.75; // Using 80% of cell size

        // Calculate top-left corner of the piece
        double x = centerX - (diameter / 2.0);
        double y = centerY - (diameter / 2.0) - 7.0;

        // Draw piece
        Ellipse2D.Double circle = new Ellipse2D.Double(x, y, diameter, diameter);

        // Draw the piece with gradient for 3D effect
        GradientPaint gp;
        if (PIECE_COLOR.BLACK == color) {
            gp = new GradientPaint((float)x, (float)y, Color.GRAY,
                    (float)(x + diameter), (float)(y + diameter), Color.BLACK);
        } else {
            gp = new GradientPaint((float)x, (float)y, Color.WHITE,
                    (float)(x + diameter), (float)(y + diameter), Color.LIGHT_GRAY);
        }

        g2d.setPaint(gp);
        g2d.fill(circle);

        // Add border for definition
        g2d.setColor(PIECE_COLOR.BLACK == color ? Color.BLACK : Color.GRAY);
        g2d.setStroke(new BasicStroke(1.5f));
        g2d.draw(circle);
    }

    /**
     * Flip the color of the piece
     * */
    public void flip() {
        this.color = color == PIECE_COLOR.BLACK ? PIECE_COLOR.WHITE : PIECE_COLOR.BLACK;
        repaint();
    }
}
