package project;

public class Sudoku {

    int [][] grid;

    public Sudoku(int[][] grid) {
        this.grid = grid;
    }

    public int[][] getGrid() {
        return grid;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            result.append("\n");
            for (int j = 0; j < 9; j++)
                result.append(grid[i][j]).append(" ");
        }
        return result.toString();
    }
}
