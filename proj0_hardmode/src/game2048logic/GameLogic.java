package game2048logic;

import game2048rendering.Side;

import static game2048logic.MatrixUtils.rotateLeft;
import static game2048logic.MatrixUtils.rotateRight;

/**
 * @author Josh Hug
 */
public class GameLogic {
    static void verticallyReverse(int[][] board) {
        int n = board.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                int temp = board[i][j];
                board[i][j] = board[n - 1 - i][j];
                board[n - 1 - i][j] = temp;
            }
        }
    }

    static void prework(int[][] board, Side side) {
        switch (side) {
            case NORTH:
                break;
            case EAST:
                rotateLeft(board);
                break;
            case WEST:
                rotateRight(board);
                break;
            case SOUTH:
                verticallyReverse(board);
                break;
        }
    }

    static void postwork(int[][] board, Side side) {
        switch (side) {
            case NORTH:
                break;
            case EAST:
                rotateRight(board);
                break;
            case WEST:
                rotateLeft(board);
                break;
            case SOUTH:
                verticallyReverse(board);
                break;
        }
    }

    static void moveUp(int[][] board) {
        int n = board.length;
        for (int j = 0; j < n; ++j) {
            int[] temp = new int[n];
            int index = 0;

            for (int[] ints : board) {
                if (ints[j] != 0) {
                    temp[index++] = ints[j];
                }
            }

            for (int i = 0; i < n; ++i) {
                if (i < index) {
                    board[i][j] = temp[i];
                } else {
                    board[i][j] = 0;
                }
            }
        }
    }

    static void handleMerge(int[][] board) {
        int n = board.length;
        for (int j = 0; j < n; ++j) {
            for (int i = 1; i < n; ++i) {
                if (board[i][j] != 0 && board[i][j] == board[i - 1][j]) {
                    board[i - 1][j] *= 2;
                    board[i][j] = 0;
                }
            }
        }
    }

    /**
     * Modifies the board to simulate tilting the entire board to
     * the given side.
     *
     * @param board the current state of the board
     * @param side  the direction to tilt
     */
    public static void tilt(int[][] board, Side side) {
        prework(board, side);
        moveUp(board);
        handleMerge(board);
        moveUp(board);
        postwork(board, side);
    }
}
