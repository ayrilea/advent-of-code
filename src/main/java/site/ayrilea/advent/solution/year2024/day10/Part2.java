package site.ayrilea.advent.solution.year2024.day10;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

@SolutionMetadata(year = 2024, day = 10, part = 2)
public class Part2 implements Solution<Integer> {

    @Override
    public Integer solve(Input input) {
        return Shared.solve(input, Part2::trailheadScore);
    }

    private static int trailheadScore(int[][] map, int row, int column) {
        return trailheadScore(map, row, column, 0);
    }

    private static int trailheadScore(int[][] map, int row, int column, int height) {
        if (height == 9) {
            return 1;
        }

        int score = 0;
        //Up
        if (row - 1 >= 0 && map[row - 1][column] == height + 1) {
            score += trailheadScore(map, row - 1, column, height + 1);
        }
        //Down
        if (row + 1 <= map.length - 1 && map[row + 1][column] == height + 1) {
            score += trailheadScore(map, row + 1, column, height + 1);
        }
        //Left
        if (column - 1 >= 0 && map[row][column - 1] == height + 1) {
            score += trailheadScore(map, row, column - 1, height + 1);
        }
        //Right
        if (column + 1 <= map[0].length - 1 && map[row][column + 1] == height + 1) {
            score += trailheadScore(map, row, column + 1, height + 1);
        }

        return score;
    }
}
