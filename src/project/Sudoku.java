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


    public void swapRandomRowsInRandomArea() {

        System.out.println();
        System.out.println("--------");
        System.out.println();

        for (int i = 0; i < 9; i++) {
            System.out.println();
            for (int j = 0; j < 9; j++) {
                System.out.print(grid[i][j] + " ");
            }
        }
        System.out.println();
        System.out.println("--------");
        System.out.println();


        Random random = new Random();
        int area = random.nextInt(3);
        int row1 = random.nextInt(3);
        int row2 = random.nextInt(3);
        while (row1 == row2)
            row2 = random.nextInt(3);
        row1 = area * 3 + row1;
        row2 = area * 3 + row2;

        int[] temp = grid[row1];
        grid[row1] = grid[row2];
        grid[row2] = temp;

        System.out.println("row1 = " + row1);
        System.out.println("row2 = " + row2);
        for (int i = 0; i < 9; i++) {
            System.out.println();
            for (int j = 0; j < 9; j++) {
                System.out.print(grid[i][j] + " ");
            }
        }
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
