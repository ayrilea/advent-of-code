package site.ayrilea.advent.solution.year2024.day10;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

@SolutionMetadata(year = 2024, day = 10, part = 1)
public class Part1 implements Solution<Integer> {

    @Override
    public Integer solve(Input input) {
        int[][] map = parseInput(input);

        int total = 0;
        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map[0].length; column++) {
                int height = map[row][column];
                if (height == 0) {
                    Set<Position> trailheads = new LinkedHashSet<>();
                    getTrailheads(map, row, column, height, trailheads);
                    System.out.println(trailheads.size());
                    total += trailheads.size();
                }
            }
        }

        return total;
    }

    private static void getTrailheads(int[][] map, int row, int column, int height, Set<Position> trailheads) {
        if (height == 9) {
            trailheads.add(new Position(row, column));
        }

        //Up
        if (row - 1 >= 0 && map[row - 1][column] == height + 1) {
            getTrailheads(map, row - 1, column, height + 1, trailheads);
        }
        //Down
        if (row + 1 <= map.length - 1 && map[row + 1][column] == height + 1) {
            getTrailheads(map, row + 1, column, height + 1, trailheads);
        }
        //Left
        if (column - 1 >= 0 && map[row][column - 1] == height + 1) {
            getTrailheads(map, row, column - 1, height + 1, trailheads);
        }
        //Right
        if (column + 1 <= map[0].length - 1 && map[row][column + 1] == height + 1) {
            getTrailheads(map, row, column + 1, height + 1, trailheads);
        }
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
