package Application.GameLogic;

import Application.Constants;
import Application.Constants.PIECE_COLOR;
import Application.GameObject.GameBoard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A class that contains all the logic of the Othello game
 * */
public final class OthelloLogic {

    /**
     * Enum representing the directions to check for after a piece is placed
     * */
    private enum Direction {
        T, D, LEFT, RIGHT, TL, TR, BL, BR
    }

    /**
     * A map from Direction to the coordinates delta to move in that direction
     * */
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

    /**
     * Visit each cell and determine whether a piece of "color" can be placed at that location, and return a List of
     * coordinates for cells that meet that criteria
     * @param board GameBoard we are evaluating
     * @param color Piece color for which we are checking if it is place-able or not
     * @return a List of 2-d coordinates representing locations where a piece can be placed
     * */
    public static List<List<Integer>> getPlaceableLocations(GameBoard board, PIECE_COLOR color) {
        List<List<Integer>> allPossibleLocs = new ArrayList<>();
        for (int row = 0; row < Constants.NUM_ROW; row++) {
            for (int col = 0; col < Constants.NUM_COL; col++) {
                if (!board.canPlacePieceAt(row, col)) {
                   // Piece already placed
                   continue;
                }
                // Check for sandwich
                for (Map.Entry<Direction, List<Integer>> entry: DIRS_MAP.entrySet()) {
                    Direction dir = entry.getKey();
                    List<Integer> delta = entry.getValue();
                    if(isSandwich(board, color, row + delta.get(0), col + delta.get(1), dir, 0)) {
                        // If it sandwiches another piece of different color in at least one direction, we can place
                        // a piece there
                        allPossibleLocs.add(List.of(row, col));
                        break;
                    }
                }
            }
        }
        return allPossibleLocs;
    }

    private static boolean isSandwich(GameBoard board, PIECE_COLOR color, int row, int col, Direction dir, int depth) {
        // 0. Bounds check
        if (board.isOutOfBounds(row, col)) {
            return false;
        }
        // 1. Check the existence for a piece
        if (board.canPlacePieceAt(row, col)) {
            // If no piece, then no flip can happen -> return false
            return false;
        }
        // 2. Check the color of the piece
        PIECE_COLOR currColor = board.getPieceColorAt(row, col);
        if (currColor != color) {
            // If the piece color is not the same, we traverse in the same direction
            List<Integer> delta = DIRS_MAP.get(dir);
            return isSandwich(board, color, row + delta.get(0), col + delta.get(1), dir,depth + 1);
        }

        /*
         * Piece color is the same
         * 1. If depth > 0, that means we have at least one piece of different color between the current piece and the
         *    original cell location we were checking -> true
         * 2. If depth == 0, that means we have NOT had a "sandwich" situation, so no flipping can happen -> false
         */
        return depth != 0;
    }

    /**
     * Recursively traverse the board in the direction given by "dir" and record all the pieces that need to be flipped,
     * returning whether flipping should happen. if the direction .
     * @param board GameBoard
     * @param traversed A List of 2-d coordinates that need to be flipped
     * @param currRow Current Row
     * @param currCol Current Col
     * @param placedColor Piece color that was placed
     * @param sandwich A boolean indicating whether "sandwich" happened. For instance, if Black was placed, we need at
     *                 least one White piece before any sort of flipping can happen
     * @param dir Direction in which we are moving
     * @return true if we want to flip a piece
     * */
    private static boolean traverse(GameBoard board, List<List<Integer>> traversed,
                                    int currRow, int currCol, PIECE_COLOR placedColor,
                                    Boolean sandwich, Direction dir) {
        /*
         * 4 scenarios:
         * (a) Out-of-bounds cell
         * (b) Empty cell
         * (c) Cell with the SAME color as the piece placed
         * (d) Cell with the DIFFERENT color than the piece placed
         */

        // (a) and (b) -> no flipping
        if (board.isOutOfBounds(currRow, currCol) || board.canPlacePieceAt(currRow, currCol)) {
            return false;
        }

        // (c)
        if (board.getPieceColorAt(currRow, currCol) == placedColor) {
            // If we don't sandwich a different-color piece, no flipping is needed
            return sandwich;
        }

        // (d) -> need to keep going
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
