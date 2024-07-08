import java.util.Random;

public class SudokuSolver {

    // Public method to start solving from the first cell
    public static void solveSudoku(SudokuBoard board) {
        solveSudoku(board, 0, 0);
    }

    // Private recursive method for solving the Sudoku board
    private static boolean solveSudoku(SudokuBoard board, int i, int j) {
        int sizeOfBoard = board.getSize();  // Assuming this returns n for an nxn board
        int totalSize = sizeOfBoard * sizeOfBoard;

        // If we've filled all rows, the solution is complete
        if (i == totalSize) {
            return true;
        }

        // Calculate the next cell's coordinates
        int nextRow = (j == totalSize - 1) ? i + 1 : i;
        int nextColumn = (j == totalSize - 1) ? 0 : j + 1;

        // If the current cell is fixed, skip to the next cell
        if (board.getCell(i, j).getFixed()) {
            return solveSudoku(board, nextRow, nextColumn);
        }

        // Try each number from 1 to totalSize
        for (int num = 1; num <= totalSize; num++) {
            board.getCell(i, j).setValue(num);
            if (board.checkIfValueIsOk(i, j) && solveSudoku(board, nextRow, nextColumn)) {
                return true;  // If it's valid and the rest of the board can be solved, we're done
            }
        }

        // If no number works, reset the cell and backtrack
        board.getCell(i, j).setValue(0);
        return false;
    }

}
