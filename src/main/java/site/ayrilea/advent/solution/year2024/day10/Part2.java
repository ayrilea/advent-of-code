package site.ayrilea.advent.solution.year2024.day10;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import static site.ayrilea.advent.solution.year2024.day10.Shared.parseInput;

@SolutionMetadata(year = 2024, day = 10, part = 2)
public class Part2 implements Solution<Integer> {

    @Override
    public Integer solve(Input input) {
        int[][] map = parseInput(input);

        int total = 0;
        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map[0].length; column++) {
                int height = map[row][column];
                if (height == 0) {
                    total += getTrailheadsScore(map, row, column, 0);
                }
            }
        }

        return total;
    }

    private static int getTrailheadsScore(int[][] map, int row, int column, int height) {
        if (height == 9) {
            return 1;
        }

        int score = 0;
        //Up
        if (row - 1 >= 0 && map[row - 1][column] == height + 1) {
            score += getTrailheadsScore(map, row - 1, column, height + 1);
        }
        //Down
        if (row + 1 <= map.length - 1 && map[row + 1][column] == height + 1) {
            score += getTrailheadsScore(map, row + 1, column, height + 1);
        }
        //Left
        if (column - 1 >= 0 && map[row][column - 1] == height + 1) {
            score += getTrailheadsScore(map, row, column - 1, height + 1);
        }
        //Right
        if (column + 1 <= map[0].length - 1 && map[row][column + 1] == height + 1) {
            score += getTrailheadsScore(map, row, column + 1, height + 1);
        }

        return score;
    }
}
