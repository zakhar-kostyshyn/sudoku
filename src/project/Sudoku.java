package project;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

import static java.util.stream.IntStream.range;

public class Sudoku {

    int [][] grid;

    public Sudoku(int[][] grid) {
        this.grid = grid;
    }

    public void transpose() {
        grid = randomizeTranspose() ? transposeSudoku() : grid;
    }

    private boolean randomizeTranspose() {
        return new Random().nextBoolean();
    }

    private int[][] transposeSudoku() {
        return range(0, 9)
                .mapToObj(i -> Stream.of(grid).mapToInt(row -> row[i]).toArray())
                .toArray(int[][]::new);
    }

    public int[][] getGrid() {
        return grid;
    }

    @Override
    public String toString() {
        return "Sudoku{" +
                "sudoku=" + Arrays.toString(grid) +
                '}';
    }
}
