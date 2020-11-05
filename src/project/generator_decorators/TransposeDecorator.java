package project.generator_decorators;

import project.Sudoku;

import java.util.Random;
import java.util.stream.Stream;

import static java.util.stream.IntStream.range;

public class TransposeDecorator extends BaseDecorator {

    public TransposeDecorator(SudokuGenerator sudokuGenerator) {
        super(sudokuGenerator);
    }

    @Override
    public Sudoku generate() {
        return new Sudoku(transpose());
    }

    private int[][] transpose() {
        return randomizeTranspose() ? transposeSudoku() : baseGrid;
    }

    private boolean randomizeTranspose() {
        return new Random().nextBoolean();
    }

    private int[][] transposeSudoku() {
        return range(0, 9)
                .mapToObj(i -> Stream.of(baseGrid).mapToInt(row -> row[i]).toArray())
                .toArray(int[][]::new);
    }

}
