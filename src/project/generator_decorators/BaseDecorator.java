package project.generator_decorators;


import project.Sudoku;

public class BaseDecorator implements SudokuGenerator {

    private final SudokuGenerator sudokuGenerator;
    protected int[][] baseGrid;

    public BaseDecorator(SudokuGenerator sudokuGenerator) {
        this.sudokuGenerator = sudokuGenerator;
        Sudoku baseSudoku = sudokuGenerator.generate();
        baseGrid = baseSudoku.getGrid();
    }

    @Override
    public Sudoku generate() {
        return sudokuGenerator.generate();
    }
}
