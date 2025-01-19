package Application;

import Application.Components.OthelloControlPanel;
import Application.Components.OthelloFrame;
import Application.Components.OthelloGamePanel;
import Application.GameObject.GameBoard;

import javax.swing.*;
import java.awt.*;

/**
 * JFrame: a GUI window to add components to where you add components to
 *         - Set Visibility
 *         - Set Title
 *         - Set Close Event
 * JLabel: a GUI display area for a string text or image
 * JPanel: a GUI component that serves as a container for other components. Allows you to section add sections to frame
 * ==============
 * Layout Manager defines the natural layout for components within a container
 * BorderLayout: a layout manager that places components in five areas: Center, N, S, E, and W. All extra space is then
 *               placed in the center. This is the DEFAULT layout manager.
 * FlowLayout: a layout manager that places components in a row, sized at a preferred size. A new row is automatically
 *             inserted to accommodate overflow
 * GridLayout: a layout manager that places components in a grid
 */

public class OthelloApp {

    /**
     * Create the Othello Game GUI. The structure of the scene graph is
     * OthelloFrame
     *  - OthelloGamePanel
     *      - OthelloBoardPanel (contains a 8x8 grid of JPanels, each JPanel representing a single cell)
     *  - OthelloControlPanel (holds various control switches)
     * @param gameBoard GameBoard we are creating
     * */
    private static void setUpGUI(GameBoard gameBoard){
        // Othello Frame
        OthelloFrame othelloFrame = new OthelloFrame();
        // Othello GamePanel and create Board
        OthelloGamePanel gamePanel = new OthelloGamePanel(gameBoard);
        // Othello Control Panel
        OthelloControlPanel controlPanel = new OthelloControlPanel();
        // Add Panels to Frame
        othelloFrame.add(gamePanel, BorderLayout.CENTER);
        othelloFrame.add(controlPanel, BorderLayout.EAST);
    }

    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard();
        // Setup GUI
        setUpGUI(gameBoard);
        // Initialize Game
        gameBoard.initializeBoard();
    }
}

