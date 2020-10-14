package project.sudoku_decorators;

import project.Sudoku;

import java.util.Random;

public class SwapDecorator extends BaseDecorator {

    private static final int MAX_LINES = 3;
    private final Random random = new Random();

    public SwapDecorator(SudokuGenerator sudokuGenerator) {
        super(sudokuGenerator);
    }

    @Override
    public Sudoku generate() {
        return new Sudoku(swap());
    }

    private int[][] swap() {
        for (int area = 0; area < MAX_LINES; area++) {
            swapRowAndColumnAreas();
            swapColumnAndRowLinesInArea(area);
        }
        return baseGrid;
    }

    private int randomSwapIndex1;
    private int randomSwapIndex2;

    private void randomIndexesForSwap() {
        randomSwapIndex1 = random.nextInt(MAX_LINES);
        randomSwapIndex2 = random.nextInt(MAX_LINES);
        while (randomSwapIndex1 == randomSwapIndex2)
            randomSwapIndex2 = random.nextInt(MAX_LINES);
    }

    private void swapRowAndColumnAreas() {
        for (int area = 0; area < MAX_LINES; area++)
            swapColumnAndRowLinesInArea(area);
    }

    private void swapColumnAndRowLinesInArea(int area) {
        randomIndexesForSwap();
        int swapLine1 = countLineFromAreaAndRandomIndex(area, randomSwapIndex1);
        int swapLine2 = countLineFromAreaAndRandomIndex(area, randomSwapIndex2);
        swapTwoColumnsAndTwoRows(swapLine1, swapLine2);
    }

    private int countLineFromAreaAndRandomIndex(int area, int random) {
        return area * MAX_LINES + random;
    }

    private void swapTwoColumnsAndTwoRows(int line1, int line2) {
        swapRows(line1, line2);
        for (int i = 0; i < 9; i++) {
            int temp = baseGrid[i][line1];
            baseGrid[i][line1] = baseGrid[i][line2];
            baseGrid[i][line2] = temp;
        }
    }

    private void swapRows(int r1, int r2) {
        int[] temp = baseGrid[r1];
        baseGrid[r1] = baseGrid[r2];
        baseGrid[r2] = temp;
    }
}
