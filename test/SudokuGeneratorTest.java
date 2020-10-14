
import org.junit.jupiter.api.Test;
import project.Sudoku;
import project.SudokuValidator;
import project.sudoku_decorators.BaseSudoku;
import project.sudoku_decorators.SudokuGenerator;
import project.sudoku_decorators.SwapDecorator;
import project.sudoku_decorators.TransposeDecorator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static java.lang.Math.floor;
import static java.lang.Math.random;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SudokuGeneratorTest {

    int[][] grid =
            {
                    {1, 2, 3, 4, 5, 6, 7, 8, 9},
                    {4, 5, 6, 7, 8, 9, 1, 2, 3},
                    {7, 8, 9, 1, 2, 3, 4, 5, 6},
                    {2, 3, 4, 5, 6, 7, 8, 9, 1},
                    {5, 6, 7, 8, 9, 1, 2, 3, 4},
                    {8, 9, 1, 2, 3, 4, 5, 6, 7},
                    {3, 4, 5, 6, 7, 8, 9, 1, 2},
                    {6, 7, 8, 9, 1, 2, 3, 4, 5},
                    {9, 1, 2, 3, 4, 5, 6, 7, 8},
            };

    Sudoku sudoku = new Sudoku(grid);
    SudokuValidator validator = new SudokuValidator(sudoku);

    @Test
    void testFloorWithRandom() {
        range(0, 100).map(i -> (int) floor(random() * 10)).forEach(System.out::println);
    }

    @Test
    void testListOf() {
       for (int i = 0; i < 100; i++) {
           List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
           Collections.shuffle(list);
           System.out.println("list = " + list);
       }
    }

    @Test
    void testCollectionSwap() {
        List<Integer> test = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
        Collections.swap(test, 0, 8);
        System.out.println("test = " + test);
        assertEquals(List.of(9, 2, 3, 4, 5, 6, 7, 8, 1), test);
    }

    @Test
    void testCollectionsRotate() {
        List<Integer> test = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
        Collections.rotate(test, 3);
        System.out.println("test = " + test);
        assertEquals(List.of(7, 8, 9, 1, 2, 3, 4, 5, 6), test);
    }

    @Test
    void testRandom() {
        Random random = new Random(1);
        for (int i = 0; i < 10; i++)
            System.out.println(random.nextInt(3));
    }

    @Test
    void testTranspose() {
        int [][] work =
                {
                        {1, 2, 3, 4, 5, 6, 7, 8, 9},
                        {4, 5, 6, 7, 8, 9, 1, 2, 3},
                        {7, 8, 9, 1, 2, 3, 4, 5, 6},
                        {2, 3, 4, 5, 6, 7, 8, 9, 1},
                        {5, 6, 7, 8, 9, 1, 2, 3, 4},
                        {8, 9, 1, 2, 3, 4, 5, 6, 7},
                        {3, 4, 5, 6, 7, 8, 9, 1, 2},
                        {6, 7, 8, 9, 1, 2, 3, 4, 5},
                        {9, 1, 2, 3, 4, 5, 6, 7, 8},
                };
        int [][] expected =
                {
                        {1, 4, 7, 2, 5, 8, 3, 6, 9},
                        {2, 5, 8, 3, 6, 9, 4, 7, 1},
                        {3, 6, 9, 4, 7, 1, 5, 8, 2},
                        {4, 7, 1, 5, 8, 2, 6, 9, 3},
                        {5, 8, 2, 6, 9, 3, 7, 1, 4},
                        {6, 9, 3, 7, 1, 4, 8, 2, 5},
                        {7, 1, 4, 8, 2, 5, 9, 3, 6},
                        {8, 2, 5, 9, 3, 6, 1, 4, 7},
                        {9, 3, 6, 1, 4, 7, 2, 5, 8},
                };
//        assertEquals(new Sudoku(expected), new Sudoku(work).transpose());     TODO test private transpose (find how to test private methods )
    }

    @Test
    void test1() {
        String test = "test";

        int l1 = test.length();
        System.out.println("length = " + l1);

        int l2 = test.codePointCount(0, test.length());
        System.out.println("l2 = " + l2);
    }

    @Test
    void test2() {
        final double d = (double)1 / 2;
    }

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
        DIS(sudoku);
        assertTrue(validator.check());
    }

    private void DIS(Sudoku sudoku) {
        System.out.println("---------");
        int [][] grid = sudoku.getGrid();
        for (int i = 0; i < 9; i++) {
            System.out.println();
            for (int j = 0; j < 9; j++) {
                System.out.print(grid[i][j] + " ");
            }
        }
    }

}
