package site.ayrilea.advent.solution.year2024.day4;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import static site.ayrilea.advent.solution.year2024.day4.Shared.sumCountForEachElement;
import static site.ayrilea.advent.solution.year2024.day4.Shared.parseInput;

@SolutionMetadata(year = 2024, day = 4, part = 1)
public class Part1 implements Solution<Integer> {

    @Override
    public Integer solve(Input input) {
        return sumCountForEachElement(parseInput(input), Part1::getXmasCount);
    }

    private static int getXmasCount(int[][] values, int row, int column) {
        int count = 0;
        if (values[row][column] == 0) {
            if (row > 2) {
                //Up Left
                if (column > 2) {
                    if (isXmas(values, row, column, -1, -1)) {
                        count++;
                    }
                }
                //Up
                if (isXmas(values, row, column, -1, 0)) {
                    count++;
                }
                //Up Right
                if (column < values[0].length - 3) {
                    if (isXmas(values, row, column, -1, 1)) {
                        count++;
                    }
                }
            }
            //Left
            if (column > 2) {
                if (isXmas(values, row, column, 0, -1)) {
                    count++;
                }
            }
            //Right
            if (column < values[0].length - 3) {
                if (isXmas(values, row, column, 0, 1)) {
                    count++;
                }
            }
            if (row < values.length - 3) {
                //Down Left
                if (column > 2) {
                    if (isXmas(values, row, column, 1, -1)) {
                        count++;
                    }
                }
                //Down
                if (isXmas(values, row, column, 1, 0)) {
                    count++;
                }
                //Down Right
                if (column < values[0].length - 3) {
                    if (isXmas(values, row, column, 1, 1)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private static boolean isXmas(int[][] values, int row, int column, int vertical, int horizontal) {
        return values[row + vertical][column + horizontal] == 1 &&
                values[row + 2 * vertical][column + 2 * horizontal] == 2 &&
                values[row + 3 * vertical][column + 3 * horizontal] == 3;
    }
}
