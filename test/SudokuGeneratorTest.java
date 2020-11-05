
import org.junit.jupiter.api.Test;
import project.Sudoku;
import project.SudokuValidator;
import project.generator_decorators.BaseSudoku;
import project.generator_decorators.SudokuGenerator;
import project.generator_decorators.SwapDecorator;
import project.generator_decorators.TransposeDecorator;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SudokuGeneratorTest {

    @Test
    void testBaseSudoku() {
       SudokuGenerator sudokuGenerator = new BaseSudoku();
       checkSudokuValidation(sudokuGenerator);
    }

    @Test
    void testTransposeAndBaseSudoku() {
       SudokuGenerator sudokuGenerator = new TransposeDecorator(new BaseSudoku());
       checkSudokuValidation(sudokuGenerator);
    }

    @Test
    void testSwapAndBaseSudoku() {
        SudokuGenerator sudokuGenerator = new SwapDecorator(new BaseSudoku());
        checkSudokuValidation(sudokuGenerator);
    }

    @Test
    void testSwapTransposeBaseSudoku() {
        SudokuGenerator sudokuGenerator = new SwapDecorator(
                new TransposeDecorator(
                        new BaseSudoku()));
        checkSudokuValidation(sudokuGenerator);
    }

    private void checkSudokuValidation(SudokuGenerator sudokuGenerator) {
        Sudoku sudoku = sudokuGenerator.generate();
        SudokuValidator validator = new SudokuValidator(sudoku);
        System.out.println(sudoku);
        assertTrue(validator.check());
    }
}
