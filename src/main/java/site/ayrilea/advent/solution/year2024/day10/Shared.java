package site.ayrilea.advent.solution.year2024.day10;

import site.ayrilea.advent.input.Input;

import java.util.Arrays;

public class Shared {

    static int solve(Input input, TrailheadScore trailheadScore) {
        int[][] map = parseInput(input);

        int total = 0;
        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map[0].length; column++) {
                int height = map[row][column];
                if (height == 0) {
                    total += trailheadScore.calculate(map, row, column);
                }
            }
        }

        return total;
    }

    private static int[][] parseInput(Input input) {
        return input.stream()
                .map(line -> line.split(""))
                .map(parts -> Arrays.stream(parts)
                        .map(Integer::parseInt)
                        .mapToInt(i -> i)
                        .toArray())
                .toArray(int[][]::new);
    }
}
