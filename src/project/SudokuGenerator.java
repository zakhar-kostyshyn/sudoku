package project;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.rotate;
import static java.util.Collections.shuffle;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;
import static java.util.stream.IntStream.rangeClosed;

public class SudokuGenerator {

    public Sudoku generate() {
        Sudoku sudoku = createShuffledBaseGrid();
        sudoku.transpose();
        return sudoku;
    }

    public Sudoku createShuffledBaseGrid() {
        List<Integer> baseList = rangeClosed(1, 9).boxed().collect(toList());
        shuffle(baseList);
        return new Sudoku(createBaseGrid(baseList));
    }

    private int[][] createBaseGrid(List<Integer> baseList) {
        int[][] array = new int[9][9];
        range(0, 9).forEach(i -> {
            Arrays.setAll(array[i], baseList::get);
            rotate(baseList, countRotateDistanceToAchieveRightBaseGrid(i));
        });
        return array;
    }

    private int countRotateDistanceToAchieveRightBaseGrid(int index) {
        if ((index + 1) % 3 == 0)
            return 5;
        return 6;
    }


}
