package Application.GameLogic;

import Application.Constants.PIECE_COLOR;
import Application.GameObject.GameBoard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A class that contains all the logic of the Othello game
 * */
public final class OthelloLogic {

    private enum Direction {
        T, D, LEFT, RIGHT, TL, TR, BL, BR
    }

    private static final Map<Direction, List<Integer>> DIRS_MAP = Map.of(
            Direction.T, List.of(-1, 0),
            Direction.D, List.of(1, 0),
            Direction.LEFT, List.of(0, -1),
            Direction.RIGHT, List.of(0, 1),
            Direction.TL, List.of(-1, -1),
            Direction.TR, List.of(-1, 1),
            Direction.BL, List.of(1, -1),
            Direction.BR, List.of(1, 1)
    );



    /**
     * Evaluate the "board" using "color" and return a list of indies that need to be flipped.
     * @param board Board to evaluate
     * @param color Color of the piece placed
     * @return a list of 2-element lists where 1 element is the row and 2 element is the col
     */
    public static List<List<Integer>> evaluateBoard(GameBoard board, PIECE_COLOR color,
                                            int startRow, int startCol) {
        List<List<Integer>> results = new ArrayList<>();
        for (Map.Entry<Direction, List<Integer>> entry: DIRS_MAP.entrySet()) {
            traverse(board, results, startRow + entry.getValue().get(0),
                    startCol + entry.getValue().get(1), color, false, entry.getKey());
        }
        return results;
    }

    private static boolean traverse(GameBoard board, List<List<Integer>> traversed,
                                    int currRow, int currCol, PIECE_COLOR placedColor,
                                    Boolean sandwich, Direction dir) {
        /*
         * 3 scenarios:
         * (a) Empty cell
         * (b) Cell with the SAME color as the piece placed
         * (c) Cell with the DIFFERENT color than the piece placed
         *
         * We want to terminate only when (a) or (b)
         */

        // (a) -> no flipping
        if (board.canPlacePieceAt(currRow, currCol)) {
            return false;
        }

        // (b) -> terminate but need to flip all the intermediary pieces
        if (board.getPieceColorAt(currRow, currCol) == placedColor && sandwich) {
            return true;
        }

        // (c) -> need to keep going
        sandwich = true;
        List<Integer> delta = DIRS_MAP.get(dir);
        if (traverse(board, traversed, currRow + delta.get(0), currCol + delta.get(1),
                placedColor, sandwich, dir)) {
            traversed.add(List.of(currRow, currCol));
            return true;
        }

        return false;
    }
}
