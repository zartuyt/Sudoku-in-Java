import javax.swing.*;
import java.awt.*;

public class SudokuGUI extends JFrame {
    private JTextField[][] cells;
    private int gridSize;
    private SudokuBoard board;

    public SudokuGUI(int subgridSize, char difficulty) {
        gridSize = subgridSize * subgridSize; // Total grid size derived from subgrid size
        cells = new JTextField[gridSize][gridSize];

        setTitle("Sudoku Solver");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        board = new SudokuBoard(subgridSize, difficulty);
        initializeGUIBoard();

        add(createBoardPanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);

        setVisible(true);
    }

    private void initializeGUIBoard() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                SudokuCell cell = board.getCell(i, j);
                JTextField textField = new JTextField();
                textField.setHorizontalAlignment(JTextField.CENTER);
                textField.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));

                if (!cell.getFixed()) {
                    textField.setText("");
                    textField.setEditable(true);
                } else {
                    textField.setText(String.valueOf(cell.getValue()));
                    textField.setEditable(false);
                }

                cells[i][j] = textField;
            }
        }
    }

    private JPanel createBoardPanel() {
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(gridSize, gridSize, 5, 5));
        for (JTextField[] row : cells) {
            for (JTextField cell : row) {
                boardPanel.add(cell);
            }
        }
        return boardPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        JButton solveButton = new JButton("Solve");
        solveButton.addActionListener(e -> SudokuSolver.solveSudoku(board));
        buttonPanel.add(solveButton);

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> resetBoard());
        buttonPanel.add(resetButton);

        return buttonPanel;
    }

    private void resetBoard() {
    }


    public static void main(String[] args) {
        int subgridSize = Integer.parseInt(JOptionPane.showInputDialog("Enter subgrid size:"));
        char difficulty = JOptionPane.showInputDialog("Enter difficulty (e, m, h):").charAt(0);
        new SudokuGUI(subgridSize, difficulty);
    }
}
