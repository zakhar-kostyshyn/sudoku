package project;

import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.range;

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
        return range(0, 9)
                .mapToObj(i -> range(0, 9)
                        .mapToObj(j -> Integer.toString(grid[i][j]))
                        .collect(joining(" "))
                ).collect(joining("\n"));
    }

}
