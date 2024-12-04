package site.ayrilea.advent.solution.year2024.day4;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import static site.ayrilea.advent.solution.year2024.day4.Shared.sumCountForEachElement;
import static site.ayrilea.advent.solution.year2024.day4.Shared.parseInput;

@SolutionMetadata(year = 2024, day = 4, part = 2)
public class Part2 implements Solution<Integer> {

    @Override
    public Integer solve(Input input) {
        return sumCountForEachElement(parseInput(input), Part2::isCross);
    }

    private static int isCross(int[][] values, int row, int column) {
        //Cross is centered on A
        if (values[row][column] == 2)  {
            //Centre A can not be on boundary
            if (row > 0 && row < values.length - 1 && column > 0 && column < values[0].length - 1) {
                int topLeft = values[row - 1][column - 1];
                int topRight = values[row - 1][column + 1];
                int bottomLeft = values[row + 1][column - 1];
                int bottomRight = values[row + 1][column + 1];

                if (isMas(topLeft, bottomRight) && isMas(topRight, bottomLeft)) {
                    return 1;
                }
            }
        }
        return 0;
    }

    private static boolean isMas(int first, int second) {
        return first == 1 && second == 3 || first == 3 && second == 1;
    }
}
