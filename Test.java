import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask for the size of the Sudoku subgrid (n, where the board size will be n*n)
        System.out.println("Enter the size of the Sudoku subgrid (e.g., 3 for a 9x9 board): ");
        int subgridSize = scanner.nextInt();

        // Ask for the difficulty level
        System.out.println("Enter the difficulty level (e for easy, m for medium, h for hard): ");
        char difficulty = scanner.next().charAt(0);

        // Create a new Sudoku board with the specified size and difficulty
        SudokuBoard board = new SudokuBoard(subgridSize, difficulty);

        // Print the original puzzle
        System.out.println("Original Puzzle:");
        board.print();

        // Time the solving process
        long startTime = System.nanoTime();
        SudokuSolver.solveSudoku(board);
        long endTime = System.nanoTime();

        // Print the solved puzzle
        System.out.println("Solved Puzzle:");
        board.print();

        // Calculate and print the duration in milliseconds
        long duration = (endTime - startTime) / 1_000_000; // convert from nanoseconds to milliseconds
        System.out.println("Solving took " + duration + " milliseconds.");
    }
}
