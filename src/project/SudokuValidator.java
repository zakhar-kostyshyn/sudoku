package project;

import java.util.stream.IntStream;

import static java.util.Arrays.stream;
import static java.util.stream.IntStream.*;

public class SudokuValidator {

    private final int[][] grid;

    public SudokuValidator(Sudoku sudoku) {
        this.grid = sudoku.getGrid();
    }

    public boolean check() {
        return validate3x3Square() && validateHorizontalAndVerticalLines();
    }

    private boolean validate3x3Square() {
        return startCellsRows0_3_6()
                .allMatch(row -> cellIndexesByFirstRow(row)
                        .map(cellIndex -> columnByRowAndCell(row, cellIndex))
                        .mapToObj(column -> arrayFrom3x3CellByStartedRowAndColumn(row, column))
                        .allMatch(this::validateArray));
    }

    private IntStream startCellsRows0_3_6() {
        return iterate(0, row -> row + 3).limit(3);
    }

    private IntStream cellIndexesByFirstRow(int firstRowInCell) {
        int lastRowInCell = firstRowInCell + 2;
        return rangeClosed(firstRowInCell, lastRowInCell);
    }

    private int columnByRowAndCell(int row, int cellIndex) {
        return (cellIndex - row) * 3;
    }

    private boolean validateHorizontalAndVerticalLines() {
        return range(0, 9).allMatch(i ->
                        validateArray(arrayFromHorizontalLineWithIndex(i)) &&
                        validateArray(arrayFromVerticalLineWithIndex(i)));
    }

    private int[] arrayFromHorizontalLineWithIndex(int index) {
        return stream(grid[index], 0, 9).toArray();
    }

    private int[] arrayFromVerticalLineWithIndex(int index) {
        return range(0, 9).map(j -> grid[j][index]).toArray();
    }

    private int[] arrayFrom3x3CellByStartedRowAndColumn(int row, int column) {
        return range(0, 9)
                .map(i -> grid[row + rowAdder(i)][column + columnAdder(i)])
                .toArray();
    }

    private int columnAdder(int index) {
        return index - index/3 * 3;
    }

    private int rowAdder(int index) {
        return index / 3;
    }

    private boolean validateArray(int[] line) {
        return isValidCountOfNumbers(line) && isArrayContainAllNumbers(line);
    }

    private boolean isValidCountOfNumbers(int [] line) {
        return line.length == 9;
    }

    private boolean isArrayContainAllNumbers(int [] line) {
        return rangeClosed(1, 9).allMatch(index -> of(line)
                .anyMatch(element -> element == index));
    }
}





