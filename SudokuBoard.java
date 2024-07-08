import java.util.Random;

public class SudokuBoard {
    private int size;
    private SudokuCell[][] grid;

    public SudokuBoard(int[][] initialState) {
        size = (int) Math.sqrt(initialState.length);
        grid = new SudokuCell[size * size][size * size];

        for (int i = 0; i < size * size; i++) {
            for (int j = 0; j < size * size; j++) {
                boolean isFixed = initialState[i][j] != 0;
                grid[i][j] = new SudokuCell(initialState[i][j], isFixed);
            }
        }
    }

    public int getSize() {
        return size;
    }

    public SudokuCell getCell(int i, int j) {
        return grid[i][j];
    }

    public boolean checkIfValueIsOk(int i, int j) {
        int value = grid[i][j].getValue();
        if (value == 0) return false; // Can't be valid if it's 0

        // Check row and column
        for (int k = 0; k < size * size; k++) {
            if ((grid[i][k].getValue() == value && k != j) || (grid[k][j].getValue() == value && k != i)) {
                return false;
            }
        }

        // Check subgrid
        int subRow = (i / size) * size;
        int subCol = (j / size) * size;

        for (int r = subRow; r < subRow + size; r++) {
            for (int c = subCol; c < subCol + size; c++) {
                if ((r != i || c != j) && grid[r][c].getValue() == value) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean checkIfIsSolved() {
        for (int i = 0; i < size * size; i++) {
            for (int j = 0; j < size * size; j++) {
                if (grid[i][j].getValue() == 0 || !checkIfValueIsOk(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void print() {
        int cellWidth = Integer.toString(size * size).length() + 2;
        String cellFormat = "%" + cellWidth + "d";

        for (int i = 0; i < size * size; i++) {
            if (i % size == 0) {
                int lineLength = (cellWidth + 1) * size * size + size + 1;
                System.out.println("-".repeat(lineLength));
            }
            for (int j = 0; j < size * size; j++) {
                if (j % size == 0) System.out.print("|");
                System.out.printf(cellFormat, grid[i][j].getValue());
            }
            System.out.println("|");
        }
        int lineLength = (cellWidth + 1) * size * size + size + 1;
        System.out.println("-".repeat(lineLength));
    }
        // New constructor for generating puzzles from scratch
    public SudokuBoard(int size, char difficulty) {
        this.size = size;
        grid = new SudokuCell[size * size][size * size];

        // Initialize empty grid
        for (int i = 0; i < size * size; i++) {
            for (int j = 0; j < size * size; j++) {
                grid[i][j] = new SudokuCell(0, false);
            }
        }

        // Solve the board to get a full valid Sudoku
        SudokuSolver.solveSudoku(this);

        // Determine the number of cells to empty based on difficulty
        int totalCells = size * size * size * size;
        int cellsToEmpty = 0;
        switch (difficulty) {
                case 'e':
                    cellsToEmpty = totalCells / 4;  // Easy: Remove 1/4 of the cells
                    break;
                case 'm':
                    cellsToEmpty = totalCells / 2;  // Medium: Remove 1/2 of the cells
                    break;
                case 'h':
                    cellsToEmpty = 3 * totalCells / 4;  // Hard: Remove 3/4 of the cells
                    break;
                default:
                    cellsToEmpty = totalCells / 4;  // Default to easy
                    break;
            }
            // Randomly empty the specified number of cells
            Random rand = new Random();
            for (int i = 0; i < cellsToEmpty; i++) {
                int row, col;
                do {
                    row = rand.nextInt(size * size);
                    col = rand.nextInt(size * size);
                } while (grid[row][col].getValue() == 0);  // Ensure we don't empty an already empty cell

                grid[row][col] = new SudokuCell(0, false);
            }

            // Fix the cells that are not emptied
            for (int i = 0; i < size * size; i++) {
                for (int j = 0; j < size * size; j++) {
                    if (grid[i][j].getValue() != 0) {
                        grid[i][j].setFixed(true);
                    }
                }
            }
        }

    }
