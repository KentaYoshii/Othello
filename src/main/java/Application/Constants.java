package Application;

import java.awt.*;

public final class Constants {

    private Constants(){}

    // GUI CONSTANTS

    // Frame
    public static final int TOTAL_PANEL_SIZE_X = 800;
    public static final int TOTAL_PANEL_SIZE_Y = 600;
    public static final int FRAME_BORDER_WIDTH = 5;
    // Game Panel
    public static final int GAME_PANEL_SIZE_X = 600;
    public static final int GAME_PANEL_SIZE_Y = 600;
    public static final int GAME_BORDER_SIZE = 50;
    // Control Panel
    public static final int CONTROL_PANEL_SIZE_X = 200;
    public static final int CONTROL_PANEL_SIZE_Y = GAME_PANEL_SIZE_Y;
    // Board Panel
    public static final int BOARD_CELL_SIZE_X = 25;
    public static final int BOARD_CELL_SIZE_Y = 25;
    public static final int BOARD_GRID_GAP = 5;
    // Piece Panel
    public static final int PIECE_SIZE = 60;

    // Colors
    public static final Color CONTROL_PANEL_BG = Color.LIGHT_GRAY;
    public static final Color GAME_PANEL_BG = new Color(255, 204, 153);
    public static final Color BOARD_PANEL_BG = Color.BLACK;
    public static final Color BOARD_CELL_BG = Color.GREEN;
    public static final Color BOARD_CELL_HIGHLIGHT_BG = Color.orange;

    // Game Constants

    public static final int NUM_ROW = 8;
    public static final int NUM_COL = 8;

    // Represents the piece color
    public enum PIECE_COLOR {
        BLACK, WHITE
    }

    // In Othello, Black always starts first
    public static final PIECE_COLOR INITIAL_TURN = PIECE_COLOR.BLACK;

    // Game mode
    public enum GAME_MODE {
        HUMAN, AI
    }
}

