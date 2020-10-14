package project.decorator;

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

            int random1 = random.nextInt(MAX_LINES);
            int random2 = random.nextInt(MAX_LINES);
            while (random1 == random2)
                random2 = random.nextInt(MAX_LINES);

            swapRowAndColumnAreas(area, random2);

            int swapLine1 = area * MAX_LINES + random1;
            int swapLine2 = area * MAX_LINES + random2;
            swapRows(swapLine1, swapLine2);
            swapColumns(swapLine1, swapLine2);
        }
        return baseGrid;
    }

    private void swapRowAndColumnAreas(int area1, int area2) {
        for (int i = 0; i < MAX_LINES; i++) {
            int swapArea1 = i * MAX_LINES + area1;
            int swapArea2 = i * MAX_LINES + area2;
            swapRows(swapArea1, swapArea2);
            swapColumns(swapArea1, swapArea2);
        }
    }
    private  void swapRows(int row1, int row2) {
        int[] temp = baseGrid[row1];
        baseGrid[row1] = baseGrid[row2];
        baseGrid[row2] = temp;
    }

    private  void swapColumns(int column1, int column2) {
        for (int i = 0; i < 9; i++) {
            int temp = baseGrid[i][column1];
            baseGrid[i][column1] = baseGrid[i][column2];
            baseGrid[i][column2] = temp;
        }
    }

}
